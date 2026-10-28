package knav;

/** Bounded coordinate/path validation, independent of the live game. */
public final class NavLogic {
    public static boolean exitArrival(MapPacket source, MapPacket destination, boolean nearExitAtReceipt) {
        // A fresh command-12 load may loop back into the same map/area.
        return source.id != destination.id || source.area != destination.area || nearExitAtReceipt;
    }
    public static int abs(int n) { return n < 0 ? -n : n; }
    public static boolean walkable(int value) { return value == 0 || value == 2; }
    public static int approach(MapPacket map, int[] collision, MapPacket.Exit exit) {
        if (collision == null || collision.length != map.width * map.height) return -1;
        int best = -1, score = Integer.MAX_VALUE;
        int tx = exit.x / 24, ty = exit.y / 24;
        int side = outward(map, exit);
        for (int x = tx - 4; x <= tx + 4; x++) for (int y = ty - 4; y <= ty + 4; y++) {
            if (x < 1 || y < 1 || x >= map.width - 1 || y >= map.height - 1) continue;
            if (!walkable(collision[y * map.width + x])) continue;
            // Approach from inside, then cross the raw marker. Do not choose
            // an equally near tile already beyond the trigger line.
            int cx = x * 24 + 12, cy = y * 24 + 12;
            if (side == 0 && cx < exit.x + 12 || side == 1 && cx > exit.x - 12
                    || side == 2 && cy < exit.y + 12 || side == 3 && cy > exit.y - 12) continue;
            int dx = x * 24 + 12 - exit.x, dy = y * 24 + 12 - exit.y;
            int distance = dx * dx + dy * dy;
            if (distance < score) { score = distance; best = (x << 8) | y; }
        }
        return best;
    }
    public static boolean validPath(short[] path, int sx, int sy, int dx, int dy,
                                    int width, int height, int[] collision, int limit) {
        if (width < 1 || height < 1 || width >= 128 || height >= 128 || collision == null
                || collision.length != width * height || sx < 0 || sy < 0 || sx >= width || sy >= height) return false;
        if (path == null || path.length < 1 || path.length > limit) return false;
        int px = sx, py = sy;
        for (int i = 0; i < path.length; i++) {
            int x = (path[i] >> 8) & 255, y = path[i] & 255;
            if (x >= width || y >= height || x >= 128 || y >= 128
                    || !walkable(collision[y * width + x]) || abs(x - px) + abs(y - py) > 1) return false;
            px = x; py = y;
        }
        return px == dx && py == dy;
    }
    /** 0 left, 1 right, 2 up, 3 down; only border-near transport markers. */
    public static int outward(MapPacket map, MapPacket.Exit exit) {
        int[] distances = new int[]{exit.x, map.width * 24 - exit.x, exit.y, map.height * 24 - exit.y};
        int best = 0;
        for (int i = 1; i < 4; i++) if (distances[i] < distances[best]) best = i;
        return distances[best] <= 100 ? best : -1;
    }
    public static int toward(int x, int y, int targetX, int targetY) {
        int dx = targetX - x, dy = targetY - y;
        if (abs(dx) > 5) return dx < 0 ? 0 : 1;
        if (abs(dy) > 5) return dy < 0 ? 2 : 3;
        return -1;
    }
}
