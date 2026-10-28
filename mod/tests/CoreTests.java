import java.io.*;
import knav.MapPacket;
import knav.NavLogic;
import knav.CaptureFormat;
import knav.NavGraph;

public final class CoreTests {
    static byte[] fixture(int width, int height, int exits) throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(); DataOutputStream out = new DataOutputStream(bytes);
        out.writeShort(8); out.writeShort(2); out.writeShort(3); out.writeShort(8); out.writeUTF("Valley \u00e9\u0000\ud83d\ude00");
        out.writeShort(3 + width * height); out.writeByte(width); out.writeByte(height); out.writeByte(0);
        for (int i = 0; i < width * height; i++) out.writeByte(1);
        out.writeByte(-1); out.writeShort(0); out.writeByte(exits);
        for (int i = 0; i < exits; i++) { out.writeShort(0); out.writeShort(48); out.writeUTF("West"); }
        out.writeByte(0); out.writeByte(5); out.writeByte(0); out.writeBoolean(false); out.writeBoolean(false);
        return bytes.toByteArray();
    }
    static MapPacket map(int id) throws Exception {
        byte[] raw = fixture(10, 8, 1); raw[0] = (byte)(id >> 8); raw[1] = (byte)id;
        return MapPacket.parse(raw);
    }
    public static void main(String[] args) throws Exception {
        MapPacket map = MapPacket.parse(fixture(10, 8, 1));
        assert map.id == 8 && map.area == 5 && map.spawnX == 48 && map.spawnY == 72;
        assert map.width == 10 && map.height == 8 && map.exits[0].name.equals("West");
        assert map.name.equals("Valley \u00e9\u0000\ud83d\ude00") && map.raw.length > map.terrain.length;
        int[] collision = new int[80];
        assert NavLogic.approach(map, collision, map.exits[0]) == ((1 << 8) | 1);
        assert NavLogic.outward(map, map.exits[0]) == 0;
        map.exits[0].x = 48;
        assert (NavLogic.approach(map, collision, map.exits[0]) >> 8) == 2;
        map.exits[0].x = 0;
        assert NavLogic.validPath(new short[]{0x0101, 0x0201, 0x0301}, 1, 1, 3, 1, 10, 8, collision, 100);
        assert !NavLogic.validPath(new short[101], 1, 1, 3, 1, 10, 8, collision, 100);
        assert !NavLogic.validPath(new short[]{0, 0, 0}, 1, 1, 3, 1, 10, 8, collision, 100);
        assert !NavLogic.validPath(new short[]{0x0101, 0x0301}, 1, 1, 3, 1, 10, 8, collision, 100);
        collision[12] = 1;
        assert !NavLogic.validPath(new short[]{0x0101, 0x0201, 0x0301}, 1, 1, 3, 1, 10, 8, collision, 100);
        boolean rejected = false;
        try { MapPacket.parse(new byte[]{0, 1, 2}); } catch (IOException expected) { rejected = true; }
        assert rejected;
        rejected = false;
        try { MapPacket.parse(fixture(0, 8, 0)); } catch (IOException expected) { rejected = true; }
        assert rejected;
        assert NavLogic.toward(40, 48, 12, 48) == 0;
        assert NavLogic.toward(12, 40, 12, 72) == 3;
        assert NavLogic.toward(12, 72, 12, 72) == -1;
        if (args.length == 1) {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(args[0]));
            CaptureFormat.header(out, 6);
            byte[] record = CaptureFormat.map("fixture.invalid:19129", map, new int[80], 123456789L);
            out.writeInt(record.length); out.write(record);
            MapPacket separateWorld = map(99);
            record = CaptureFormat.map("fixture.invalid:19129", separateWorld, new int[80], 123456790L);
            out.writeInt(record.length); out.write(record);
            MapPacket next = MapPacket.parse(fixture(10, 8, 1)); next.id = 9;
            record = CaptureFormat.edge("fixture.invalid:19129", map, 0, next, 123456999L);
            out.writeInt(record.length); out.write(record);
            record = CaptureFormat.external("fixture.invalid:19129", map, separateWorld, "npc", "Harbor gate", 104, 101);
            out.writeInt(record.length); out.write(record);
            NavGraph.Block blocked = GraphTests.blocked(map, 0, 104); blocked.observed = 123457000L;
            record = CaptureFormat.block(blocked); out.writeInt(record.length); out.write(record);
            record = CaptureFormat.diagnostics(2, 1); out.writeInt(record.length); out.write(record); out.close();
        }
        System.out.println("PASS: map schema/UTF/spawn/area, malformed packets, native-path failure sentinels, collision and direction");
    }
}
