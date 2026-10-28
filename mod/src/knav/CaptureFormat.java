package knav;

import java.io.*;

/** Shared, testable binary export contract. All strings use Java modified UTF-8. */
public final class CaptureFormat {
    public static final String PROFILE = "f023b34e0ba0a3dcf44249ed5f90b78f0427deee4668562b8e3a0e9e349b18c6";
    public static void header(DataOutputStream out, int count) throws IOException {
        out.writeInt(0x4b4e4156); out.writeShort(2); out.writeUTF(PROFILE); out.writeInt(count);
    }
    public static byte[] map(String server, MapPacket map, int[] collision, long observed) throws IOException {
        if (collision.length != map.width * map.height) throw new IOException("Collision dimensions mismatch");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(); DataOutputStream out = new DataOutputStream(bytes);
        out.writeByte(1); out.writeUTF(server); out.writeInt(map.id); out.writeInt(map.area);
        out.writeLong(observed); out.writeInt(map.raw.length); out.write(map.raw);
        out.writeInt(collision.length);
        for (int i = 0; i < collision.length; i++) out.writeByte(collision[i]);
        return bytes.toByteArray();
    }
    public static byte[] edge(String server, MapPacket source, int selected, MapPacket destination, long observed) throws IOException {
        MapPacket.Exit exit = source.exits[selected];
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(); DataOutputStream out = new DataOutputStream(bytes);
        out.writeByte(2); out.writeUTF(server); out.writeInt(source.id); out.writeInt(source.area);
        out.writeInt(selected); out.writeInt(exit.x); out.writeInt(exit.y); out.writeUTF(exit.name);
        out.writeInt(destination.id); out.writeInt(destination.area);
        out.writeInt(destination.spawnX); out.writeInt(destination.spawnY); out.writeLong(observed);
        return bytes.toByteArray();
    }
    public static byte[] diagnostics(int dropped, int rejected) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(); DataOutputStream out = new DataOutputStream(bytes);
        out.writeByte(3); out.writeUTF(""); out.writeInt(-1); out.writeInt(-1);
        out.writeInt(dropped); out.writeInt(rejected); return bytes.toByteArray();
    }
    public static byte[] block(NavGraph.Block block) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(); DataOutputStream out = new DataOutputStream(bytes);
        out.writeByte(5); out.writeUTF(block.server); out.writeInt(block.map); out.writeInt(block.area);
        out.writeInt(block.x); out.writeInt(block.y); out.writeUTF(block.name); out.writeUTF(block.actor);
        out.writeInt(block.level); out.writeUTF(block.reason); out.writeLong(block.observed); return bytes.toByteArray();
    }
    public static byte[] external(String server, MapPacket source, MapPacket destination, String kind,
                                  String note, int observedLevel, int requiredLevel) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(); DataOutputStream out = new DataOutputStream(bytes);
        out.writeByte(4); out.writeUTF(server); out.writeInt(source.id); out.writeInt(source.area);
        out.writeInt(destination.id); out.writeInt(destination.area); out.writeUTF(kind); out.writeUTF(note);
        out.writeInt(observedLevel); out.writeInt(requiredLevel); out.writeLong(System.currentTimeMillis());
        return bytes.toByteArray();
    }
}
