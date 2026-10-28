package knav;

import java.io.*;

/** Recognizes explicit level-denial wording, without inventing numeric requirements. */
public final class GateNotice {
    public String text, actor, exitName;
    public int command, map, area, actorLevel, exitX, exitY;
    public long received;
    public static GateNotice parse(int command, byte[] bytes) throws IOException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        if (command == -32 || command == -31) { in.readShort(); in.readByte(); }
        else if (command != 53) throw new IOException("Not a notice command");
        GateNotice notice = new GateNotice(); notice.command = command; notice.text = in.readUTF();
        if (notice.text.length() > 1024) notice.text = notice.text.substring(0, 1024);
        return notice;
    }
    public boolean levelDenied() {
        String value = text.toLowerCase();
        boolean level = value.indexOf("level") >= 0 || value.indexOf("c\u1ea5p") >= 0 || value.indexOf(" cap ") >= 0;
        boolean denial = value.indexOf("require") >= 0 || value.indexOf("need") >= 0 || value.indexOf("at least") >= 0
            || value.indexOf("must") >= 0 || value.indexOf("cannot") >= 0 || value.indexOf("too low") >= 0
            || value.indexOf("c\u1ea7n") >= 0 || value.indexOf("ch\u01b0a") >= 0 || value.indexOf("y\u00eau c\u1ea7u") >= 0
            || value.indexOf("ph\u1ea3i") >= 0 || value.indexOf("kh\u00f4ng") >= 0 || value.indexOf("t\u1ed1i thi\u1ec3u") >= 0;
        return level && denial && command != -31;
    }
    public boolean matches(int map, int area, MapPacket.Exit exit, String actor, int level, long now) {
        return this.map == map && this.area == area && this.exitX == exit.x && this.exitY == exit.y
            && this.exitName != null && this.exitName.equals(exit.name) && this.actor != null && this.actor.equals(actor)
            && this.actorLevel == level && now >= received && now - received <= 8000;
    }
}
