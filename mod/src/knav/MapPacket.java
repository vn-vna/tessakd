package knav;

import java.io.*;

/** The reviewed 4.0.3 command-12 schema. Does not consume the game's stream. */
public final class MapPacket {
    public static final class Exit {
        public int x, y;
        public String name;
    }
    public int id, area, width, height, tileSet, spawnX, spawnY, type;
    public String name;
    public byte[] raw, terrain;
    public Exit[] exits;

    private static byte[] blob(DataInputStream in) throws IOException {
        int length = in.readShort();
        if (length < 0 || length > in.available()) throw new IOException("Invalid map blob length");
        byte[] bytes = new byte[length]; in.readFully(bytes); return bytes;
    }
    public static MapPacket parse(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length > 65535) throw new IOException("Map packet too large");
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        MapPacket map = new MapPacket(); map.raw = bytes;
        map.id = in.readShort(); map.spawnX = in.readShort() * 24; map.spawnY = in.readShort() * 24;
        in.readShort(); map.name = in.readUTF(); map.terrain = blob(in);
        if (map.id < 0 || map.terrain.length < 3) throw new IOException("Missing terrain");
        map.width = map.terrain[0]; map.height = map.terrain[1]; map.tileSet = map.terrain[2];
        if (map.width <= 0 || map.height <= 0 || map.terrain.length < 3 + map.width * map.height)
            throw new IOException("Invalid terrain dimensions");
        int background = in.readByte(); if (background >= 0) in.readShort();
        blob(in); // Map-object data; preserved in raw, not interpreted as terrain.
        int count = in.readByte();
        if (count < 0 || count > 32) throw new IOException("Unsupported exit count");
        map.exits = new Exit[count];
        for (int i = 0; i < count; i++) {
            Exit exit = new Exit(); exit.x = in.readShort(); exit.y = in.readShort(); exit.name = in.readUTF();
            if (exit.x < 0 || exit.y < 0 || exit.x > map.width * 24 || exit.y > map.height * 24)
                throw new IOException("Exit outside map");
            map.exits[i] = exit;
        }
        in.readByte(); map.area = in.readByte(); map.type = in.readByte();
        if (map.area < 0) throw new IOException("Invalid area");
        // Optional flags/trailing bytes remain preserved verbatim in raw.
        return map;
    }
}
