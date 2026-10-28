package knav;

import java.io.*;
import java.util.Vector;
import javax.microedition.rms.*;

/** Bounded RMS cache. Map records are latest observations, edges are a journal. */
public final class NavStore {
    private static RecordStore store;
    private static Vector ids = new Vector(), linkIds = new Vector(), blockIds = new Vector();
    public static NavGraph graph = new NavGraph();
    public static volatile boolean exporting;
    public static int mapCount, edgeCount, blockedCount, externalCount;
    public static final int MAX_MAPS = 192, MAX_LINKS = 512, MAX_BLOCKS = 384, MAX_EXTERNAL = 256;
    public static final int BYTE_LIMIT = 2097152;
    private static void open() throws Exception {
        if (store != null) return;
        store = RecordStore.openRecordStore("kn_nav_v1", true);
        RecordEnumeration list = store.enumerateRecords(null, null, false);
        try {
            while (list.hasNextElement()) {
                int id = list.nextRecordId(); byte[] data = store.getRecord(id);
                DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
                int type = in.readUnsignedByte(); String server = in.readUTF();
                int map = in.readInt(), area = in.readInt();
                if (type == 1) {
                    in.readLong(); int length = in.readInt();
                    if (length < 0 || length > 65535) throw new IOException("Invalid stored map");
                    byte[] raw = new byte[length]; in.readFully(raw); MapPacket packet = MapPacket.parse(raw);
                    if (packet.id != map || packet.area != area) throw new IOException("Stored map identity mismatch");
                    int index = graph.put(server, packet);
                    if (index == ids.size()) ids.addElement(new Integer(id)); else ids.setElementAt(new Integer(id), index);
                    mapCount = graph.nodes.size();
                }
                else if (type == 2) {
                    NavGraph.Link link = new NavGraph.Link(); link.server = server; link.map = map; link.area = area;
                    in.readInt(); link.x = in.readInt(); link.y = in.readInt(); link.name = in.readUTF();
                    link.destination = in.readInt(); link.destinationArea = in.readInt(); in.readInt(); in.readInt();
                    link.observed = in.readLong(); int old = graph.linkIndex(server, map, area, exit(link.x, link.y, link.name));
                    boolean latest = old < 0 || ((NavGraph.Link)graph.links.elementAt(old)).observed <= link.observed;
                    int index = graph.add(link);
                    if (index == linkIds.size()) linkIds.addElement(new Integer(id)); else if (latest) linkIds.setElementAt(new Integer(id), index);
                    edgeCount = graph.links.size();
                } else if (type == 4) externalCount++;
                else if (type == 5) {
                    NavGraph.Block block = new NavGraph.Block(); block.server = server; block.map = map; block.area = area;
                    block.x = in.readInt(); block.y = in.readInt(); block.name = in.readUTF(); block.actor = in.readUTF();
                    block.level = in.readInt(); block.reason = in.readUTF(); block.observed = in.readLong();
                    int old = graph.blockIndex(block); boolean latest = old < 0 || ((NavGraph.Block)graph.blocks.elementAt(old)).observed <= block.observed;
                    int index = graph.add(block);
                    if (index == blockIds.size()) blockIds.addElement(new Integer(id)); else if (latest) blockIds.setElementAt(new Integer(id), index);
                    blockedCount = graph.blocks.size();
                } else throw new IOException("Unsupported RMS record version");
            }
        } catch (Exception error) {
            try { store.closeRecordStore(); } catch (Exception ignored) {}
            store = null; resetIndex(); throw error;
        } finally { list.destroy(); }
    }
    private static MapPacket.Exit exit(int x, int y, String name) {
        MapPacket.Exit value = new MapPacket.Exit(); value.x = x; value.y = y; value.name = name; return value;
    }
    private static int write(int id, byte[] bytes) throws Exception {
        int previous = id < 0 ? 0 : store.getRecord(id).length;
        if (store.getSize() - previous + bytes.length > BYTE_LIMIT) throw new IOException("NAV cache full; export before clearing");
        if (id < 0) return store.addRecord(bytes, 0, bytes.length);
        store.setRecord(id, bytes, 0, bytes.length); return id;
    }
    public static void init() throws Exception { open(); }
    public static void map(String server, MapPacket map, int[] collision) throws Exception {
        if (exporting) return;
        open(); int index = graph.node(server, map.id, map.area);
        byte[] record = CaptureFormat.map(server, map, collision, System.currentTimeMillis());
        if (index < 0 && mapCount >= MAX_MAPS)
            throw new IOException("Map cache limit reached; export before more recording");
        int id = write(index < 0 ? -1 : ((Integer)ids.elementAt(index)).intValue(), record);
        index = graph.put(server, map);
        if (index == ids.size()) ids.addElement(new Integer(id)); else ids.setElementAt(new Integer(id), index);
        mapCount = graph.nodes.size();
    }
    public static boolean traversed(String server, MapPacket map, MapPacket.Exit exit) {
        return graph.linkIndex(server, map.id, map.area, exit) >= 0;
    }
    public static void edge(String server, MapPacket source, int selected, MapPacket destination) throws Exception {
        if (exporting) return;
        open();
        MapPacket.Exit exit = source.exits[selected];
        int index = graph.linkIndex(server, source.id, source.area, exit);
        if (index < 0 && edgeCount >= MAX_LINKS) throw new IOException("Walking-link cache limit reached");
        long observed = System.currentTimeMillis();
        byte[] record = CaptureFormat.edge(server, source, selected, destination, observed);
        int id = write(index < 0 ? -1 : ((Integer)linkIds.elementAt(index)).intValue(), record);
        NavGraph.Link link = new NavGraph.Link(); link.server = server; link.map = source.id; link.area = source.area;
        link.x = exit.x; link.y = exit.y; link.name = exit.name; link.destination = destination.id;
        link.destinationArea = destination.area; link.observed = observed; index = graph.add(link);
        if (index == linkIds.size()) linkIds.addElement(new Integer(id)); else linkIds.setElementAt(new Integer(id), index);
        edgeCount = graph.links.size();
    }
    public static void block(String server, MapPacket map, int selected, String actor, int level, String reason) throws Exception {
        if (exporting) return;
        open(); MapPacket.Exit exit = map.exits[selected]; NavGraph.Block block = new NavGraph.Block();
        block.server = server; block.map = map.id; block.area = map.area; block.x = exit.x; block.y = exit.y;
        block.name = exit.name; block.actor = actor; block.level = level;
        block.reason = reason.length() > 1024 ? reason.substring(0, 1024) : reason; block.observed = System.currentTimeMillis();
        int index = graph.blockIndex(block);
        if (index < 0 && blockedCount >= MAX_BLOCKS) throw new IOException("Blocked-exit cache limit reached");
        int id = write(index < 0 ? -1 : ((Integer)blockIds.elementAt(index)).intValue(), CaptureFormat.block(block));
        index = graph.add(block);
        if (index == blockIds.size()) blockIds.addElement(new Integer(id)); else blockIds.setElementAt(new Integer(id), index);
        blockedCount = graph.blocks.size();
    }
    public static void retryBlocks(String server, String actor) throws Exception {
        if (exporting) return;
        open();
        // Older caches may contain multiple display labels for the same point.
        // Clear all matching records, not just the coalesced graph's latest ID.
        Vector remove = new Vector(); RecordEnumeration records = store.enumerateRecords(null, null, false);
        try {
            while (records.hasNextElement()) {
                int id = records.nextRecordId();
                DataInputStream in = new DataInputStream(new ByteArrayInputStream(store.getRecord(id)));
                if (in.readUnsignedByte() != 5) continue;
                String scope = in.readUTF(); in.readInt(); in.readInt(); in.readInt(); in.readInt(); in.readUTF();
                String owner = in.readUTF();
                if (scope.equals(server) && owner.equals(actor)) remove.addElement(new Integer(id));
            }
        } finally { records.destroy(); }
        for (int i = 0; i < remove.size(); i++) store.deleteRecord(((Integer)remove.elementAt(i)).intValue());
        for (int i = graph.blocks.size() - 1; i >= 0; i--) {
            NavGraph.Block block = (NavGraph.Block)graph.blocks.elementAt(i);
            if (!block.server.equals(server) || !block.actor.equals(actor)) continue;
            blockIds.removeElementAt(i); graph.blocks.removeElementAt(i);
        }
        blockedCount = graph.blocks.size();
    }
    public static void external(String server, MapPacket source, MapPacket target, String kind, String note, int level, int minimum) throws Exception {
        if (exporting) return;
        open(); if (externalCount >= MAX_EXTERNAL) throw new IOException("External-link journal limit reached");
        write(-1, CaptureFormat.external(server, source, target, kind, note, level, minimum)); externalCount++;
    }
    public static void exportTo(OutputStream stream) throws Exception {
        open(); DataOutputStream out = new DataOutputStream(stream);
        RecordEnumeration list = store.enumerateRecords(null, null, false);
        try {
            CaptureFormat.header(out, list.numRecords() + 1);
            while (list.hasNextElement()) {
                byte[] bytes = list.nextRecord(); out.writeInt(bytes.length); out.write(bytes);
            }
            byte[] diagnostics = CaptureFormat.diagnostics(NavMod.captureDrops, NavMod.captureRejected);
            out.writeInt(diagnostics.length); out.write(diagnostics);
            out.flush();
        } finally { list.destroy(); }
    }
    public static void clear() throws Exception {
        if (exporting) throw new IOException("Wait until export completes");
        if (store != null) { store.closeRecordStore(); store = null; }
        try { RecordStore.deleteRecordStore("kn_nav_v1"); } catch (RecordStoreNotFoundException ignored) {}
        resetIndex();
    }
    private static void resetIndex() {
        ids = new Vector(); linkIds = new Vector(); blockIds = new Vector(); graph = new NavGraph();
        mapCount = edgeCount = blockedCount = externalCount = 0;
    }
}
