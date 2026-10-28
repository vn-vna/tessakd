package knav;

import java.util.Vector;

/** Directed walking graph. External/NPC transitions are intentionally not routing links. */
public final class NavGraph {
    public static final class Node {
        public String server;
        public MapPacket map; // Geometry metadata only: never retains raw packet/terrain arrays.
    }
    public static final class Link {
        public String server, name;
        public int map, area, x, y, destination, destinationArea;
        public long observed;
    }
    public static final class Block {
        public String server, name, actor, reason;
        public int map, area, x, y, level;
        public long observed;
    }
    public static final class Step {
        public int exit, targetMap, targetArea;
        public boolean backtracking;
    }
    public final Vector nodes = new Vector(), links = new Vector(), blocks = new Vector();

    public int node(String server, int map, int area) {
        for (int i = 0; i < nodes.size(); i++) {
            Node n = (Node)nodes.elementAt(i);
            if (n.server.equals(server) && n.map.id == map && n.map.area == area) return i;
        }
        return -1;
    }
    public int put(String server, MapPacket map) {
        int index = node(server, map.id, map.area);
        MapPacket metadata = new MapPacket(); metadata.id = map.id; metadata.area = map.area;
        metadata.width = map.width; metadata.height = map.height; metadata.name = map.name; metadata.exits = map.exits;
        Node n = new Node(); n.server = server; n.map = metadata;
        if (index < 0) { nodes.addElement(n); return nodes.size() - 1; }
        nodes.setElementAt(n, index); return index;
    }
    private static boolean same(String server, int map, int area, int x, int y, Link link) {
        // Display labels can change or be translated; coordinates identify the point.
        return link.server.equals(server) && link.map == map && link.area == area && link.x == x && link.y == y;
    }
    public int linkIndex(String server, int map, int area, MapPacket.Exit exit) {
        for (int i = 0; i < links.size(); i++)
            if (same(server, map, area, exit.x, exit.y, (Link)links.elementAt(i))) return i;
        return -1;
    }
    public int add(Link link) {
        MapPacket.Exit exit = new MapPacket.Exit(); exit.x = link.x; exit.y = link.y; exit.name = link.name;
        int index = linkIndex(link.server, link.map, link.area, exit);
        if (index < 0) { links.addElement(link); return links.size() - 1; }
        if (((Link)links.elementAt(index)).observed <= link.observed) links.setElementAt(link, index);
        return index;
    }
    public boolean blocked(String server, int map, int area, MapPacket.Exit exit, String actor, int level) {
        for (int i = blocks.size() - 1; i >= 0; i--) {
            Block b = (Block)blocks.elementAt(i);
            if (b.server.equals(server) && b.map == map && b.area == area && b.x == exit.x && b.y == exit.y
                    && b.actor.equals(actor) && b.level == level) return true;
        }
        return false;
    }
    public int blockIndex(Block candidate) {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = (Block)blocks.elementAt(i);
            if (b.server.equals(candidate.server) && b.map == candidate.map && b.area == candidate.area
                    && b.x == candidate.x && b.y == candidate.y
                    && b.actor.equals(candidate.actor) && b.level == candidate.level) return i;
        }
        return -1;
    }
    public int add(Block block) {
        int index = blockIndex(block);
        if (index < 0) { blocks.addElement(block); return blocks.size() - 1; }
        if (((Block)blocks.elementAt(index)).observed <= block.observed) blocks.setElementAt(block, index);
        return index;
    }
    private int frontier(Node node, String actor, int level) {
        for (int i = 0; i < node.map.exits.length; i++) {
            MapPacket.Exit e = node.map.exits[i];
            if (NavLogic.outward(node.map, e) >= 0 && linkIndex(node.server, node.map.id, node.map.area, e) < 0
                    && !blocked(node.server, node.map.id, node.map.area, e, actor, level)) return i;
        }
        return -1;
    }
    /** BFS to the closest reachable map with an untried walking exit. */
    public Step next(String server, MapPacket current, String actor, int level) {
        int start = node(server, current.id, current.area);
        if (start < 0) return null;
        int count = nodes.size(); int[] queue = new int[count], firstExit = new int[count];
        boolean[] seen = new boolean[count]; int head = 0, tail = 0;
        queue[tail++] = start; seen[start] = true; firstExit[start] = -1;
        while (head < tail) {
            int index = queue[head++]; Node node = (Node)nodes.elementAt(index);
            int unexplored = frontier(node, actor, level);
            if (unexplored >= 0) {
                Step step = new Step(); step.exit = index == start ? unexplored : firstExit[index];
                step.targetMap = node.map.id; step.targetArea = node.map.area; step.backtracking = index != start; return step;
            }
            for (int i = 0; i < node.map.exits.length; i++) {
                MapPacket.Exit exit = node.map.exits[i];
                if (NavLogic.outward(node.map, exit) < 0 || blocked(server, node.map.id, node.map.area, exit, actor, level)) continue;
                int edge = linkIndex(server, node.map.id, node.map.area, exit);
                if (edge < 0) continue;
                Link link = (Link)links.elementAt(edge); int destination = node(server, link.destination, link.destinationArea);
                if (destination < 0 || seen[destination]) continue;
                seen[destination] = true; firstExit[destination] = index == start ? i : firstExit[index];
                queue[tail++] = destination;
            }
        }
        return null;
    }
}
