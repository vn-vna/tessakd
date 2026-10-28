import java.io.*;
import knav.*;

public final class GraphTests {
    static final String SERVER = "fixture.invalid:19129", ACTOR = "hero";
    static MapPacket node(int id, String[] names) throws Exception {
        MapPacket map = CoreTests.map(id); map.exits = new MapPacket.Exit[names.length];
        for (int i = 0; i < names.length; i++) {
            MapPacket.Exit exit = new MapPacket.Exit(); exit.x = i == 0 ? 0 : map.width * 24;
            exit.y = 48; exit.name = names[i]; map.exits[i] = exit;
        }
        return map;
    }
    static void connect(NavGraph graph, MapPacket a, int exit, MapPacket b) {
        NavGraph.Link link = new NavGraph.Link(); link.server = SERVER; link.map = a.id; link.area = a.area;
        link.x = a.exits[exit].x; link.y = a.exits[exit].y; link.name = a.exits[exit].name;
        link.destination = b.id; link.destinationArea = b.area; link.observed = 1; graph.add(link);
    }
    static NavGraph.Block blocked(MapPacket map, int index, int level) {
        NavGraph.Block block = new NavGraph.Block(); block.server = SERVER; block.actor = ACTOR;
        block.map = map.id; block.area = map.area; block.x = map.exits[index].x; block.y = map.exits[index].y;
        block.name = map.exits[index].name; block.level = level; block.reason = "Level requirement observed"; return block;
    }
    static GateNotice notice(int command, String text) throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(); DataOutputStream out = new DataOutputStream(bytes);
        if (command == -32 || command == -31) { out.writeShort(1); out.writeByte(0); }
        out.writeUTF(text); return GateNotice.parse(command, bytes.toByteArray());
    }
    static void explorationRegressions() throws Exception {
        // A->A through two distinct points: both must be tried, then terminate.
        NavGraph loops = new NavGraph(); MapPacket same = node(50, new String[]{"Loop west", "Loop east"});
        loops.put(SERVER, same); connect(loops, same, 0, same);
        assert loops.next(SERVER, same, ACTOR, 104).exit == 1;
        connect(loops, same, 1, same);
        assert loops.next(SERVER, same, ACTOR, 104) == null;
        assert NavLogic.exitArrival(same, same, true) : "Same-map transport arrival must finish the exit";
        assert !NavLogic.exitArrival(same, same, false) : "Unrelated same-map reload is not proof of crossing";
        same.exits[0].name = "Translated west"; same.exits[1].name = "Translated east";
        loops.put(SERVER, same);
        assert loops.next(SERVER, same, ACTOR, 104) == null : "Display-label changes must not reopen visited transports";

        // Discover a directed bidirectional ring from scratch, including 220
        // successful traversals: no arbitrary 100/200-hop exploration cutoff.
        final int count = 110; MapPacket[] ring = new MapPacket[count];
        for (int i = 0; i < count; i++) ring[i] = node(1000 + i, new String[]{"Clockwise", "Counterclockwise"});
        NavGraph graph = new NavGraph(); int position = 0, crossed = 0; graph.put(SERVER, ring[0]);
        while (true) {
            NavGraph.Step step = graph.next(SERVER, ring[position], ACTOR, 104);
            if (step == null) break;
            assert crossed < 1000 : "Exploration repeated an already-complete cycle";
            int destination = (position + (step.exit == 0 ? 1 : count - 1)) % count;
            connect(graph, ring[position], step.exit, ring[destination]);
            position = destination; graph.put(SERVER, ring[position]); crossed++;
        }
        assert crossed == count * 2 : "Each transport point must be explored exactly once in this ring";
        assert graph.links.size() == count * 2 && graph.nodes.size() == count;
    }
    public static void main(String[] args) throws Exception {
        explorationRegressions();
        MapPacket a = node(8, new String[]{"B", "Unseen"});
        MapPacket b = node(9, new String[]{"A"});
        MapPacket otherWorld = node(99, new String[]{"Other world exit"});
        NavGraph graph = new NavGraph(); graph.put(SERVER, a); graph.put(SERVER, b); graph.put(SERVER, otherWorld);
        connect(graph, a, 0, b);
        assert graph.next(SERVER, b, ACTOR, 104).exit == 0 : "Do not invent reverse edges; try B's untried exit";
        connect(graph, b, 0, a);
        NavGraph.Step step = graph.next(SERVER, b, ACTOR, 104);
        assert step != null && step.backtracking && step.exit == 0 && step.targetMap == a.id;
        assert graph.next(SERVER, a, ACTOR, 104).exit == 1;
        graph.add(blocked(a, 1, 104));
        assert graph.next(SERVER, b, ACTOR, 104) == null : "Disconnected/NPC-only world is not a walking route";
        a.exits[1].name = "Translated blocked point"; graph.put(SERVER, a);
        assert graph.next(SERVER, b, ACTOR, 104) == null : "Label changes must not reopen an already-tried blocked point";
        assert graph.next(SERVER, b, ACTOR, 105) != null : "New level retries observations, not permanent bans";
        assert graph.next(SERVER, b, "differentHero", 104) != null : "Restriction is character-scoped";
        graph.blocks.removeAllElements(); graph.add(blocked(b, 0, 104));
        assert graph.next(SERVER, b, ACTOR, 104) == null : "Blocked known edges cannot be used to backtrack";
        assert graph.next(SERVER, otherWorld, ACTOR, 104).exit == 0;
        assert ((NavGraph.Node)graph.nodes.elementAt(0)).map.raw == null : "Graph must not retain raw map blobs";

        assert FolderPaths.root("C:/").equals("file:///C:/");
        assert FolderPaths.root("root/").equals("file:///root/");
        assert FolderPaths.child("file:///root/", "My Maps/").equals("file:///root/My%20Maps/");
        assert FolderPaths.child("file:///E:/", "100%/").equals("file:///E:/100%25/");
        assert FolderPaths.childRaw("file:///root/My Maps/", "100%20/").equals("file:///root/My Maps/100%20/");
        assert FolderPaths.unescape("file:///root/My%20Maps/100%2520/").equals("file:///root/My Maps/100%20/");
        assert FolderPaths.parent("file:///root/My%20Maps/").equals("file:///root/");
        assert FolderPaths.parent("file:///E:/") == null;
        assert FolderPaths.child("file:///root/", "Th\u00e0nh Ph\u1ed1 C\u1ea3ng/").indexOf("%C3%A0") > 0;
        boolean rejected = false;
        try { FolderPaths.child("file:///root/", "../"); } catch (IOException expected) { rejected = true; }
        assert rejected;
        assert notice(53, "You need level 105 to enter").levelDenied();
        assert notice(-32, "B\u1ea1n c\u1ea7n \u0111\u1ea1t c\u1ea5p 105 \u0111\u1ec3 v\u00e0o").levelDenied();
        assert !notice(53, "You reached level 104!").levelDenied();
        assert !notice(-32, "Pay 5 gems to teleport?").levelDenied();
        assert !notice(-31, "You need level 105").levelDenied() : "Input dialogs must not be auto-dismissed";
        GateNotice denial = notice(53, "You need level 105"); denial.map = a.id; denial.area = a.area;
        denial.actor = ACTOR; denial.actorLevel = 104; denial.received = 1000;
        denial.exitX = a.exits[0].x; denial.exitY = a.exits[0].y; denial.exitName = a.exits[0].name;
        assert denial.matches(a.id, a.area, a.exits[0], ACTOR, 104, 1200);
        assert !denial.matches(a.id, a.area, a.exits[0], ACTOR, 105, 1200);
        assert !denial.matches(a.id, a.area, a.exits[1], ACTOR, 104, 1200);
        assert !denial.matches(a.id, a.area, a.exits[0], ACTOR, 104, 10000);
        System.out.println("PASS: exhaustive 220-crossing cyclic exploration, same-map loops, stable point identities, directed backtracking, level/character gates, disconnected worlds, folders and denial parsing");
    }
}
