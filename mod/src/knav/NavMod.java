package knav;

import CLib.mSystem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.LoadMapScreen;
import GameScreen.LoginScreen;
import GameScreen.SelectCharScreen;
import InterfaceComponents.ChatTextField;
import Main.GameCanvas;
import Thread_More.LoadMap;
import javax.microedition.lcdui.*;

/** Pass-1 live adapter. All new movement decisions run on the original update thread. */
public final class NavMod {
    public static volatile String status = "Recording maps; tap NAV";
    public static volatile MapPacket current;
    private static volatile byte[] incoming;
    private static String incomingServer, pendingServer;
    private static int incomingAttempt = -1, pendingAttempt = -1;
    private static boolean incomingNearExit, pendingNearExit;
    private static volatile int attemptSerial;
    private static volatile int receivedMapSequence, processedMapSequence;
    private static int incomingSequence, pendingSequence;
    private static volatile boolean death, stopped, shown = true;
    private static volatile boolean clearRequested;
    private static volatile String exportRequested;
    public static volatile int captureDrops, captureRejected;
    private static final Object INPUT_LOCK = new Object();
    private static volatile MapPacket requestedMap;
    private static volatile int requestedExit = -1;
    private static volatile boolean requestedContinue;
    private static volatile long requestedAt;
    private static volatile boolean retryRequested;
    private static volatile GateNotice latestNotice;
    private static volatile boolean npcArmed;
    private static MapPacket npcSource;
    private static String npcServer, npcNote;
    private static int npcMinimum, npcCharacter;
    private static long npcUntil;
    private static MapPacket pending, source;
    private static int selected, ownedKey = -1, phase, pathIndex, goalX, goalY, outward, character, replans;
    private static int progressX, progressY;
    private static short[] path;
    private static volatile boolean active;
    private static boolean keepGoing, stored, replanAfterYield, resumeOnReady;
    private static long started, crossingAt, progressAt, pendingAt;
    private static String sourceServer;
    private static String currentServer;
    private static String surveyServer, surveyActor;
    private static final int[] KEYS = new int[]{4, 6, 2, 8};

    /** Called only after the original session decoder, before Message construction. */
    public static synchronized void packet(byte command, byte[] bytes) {
        try {
            if (active && source != null && GameCanvas.loadmap != null && source.id == GameCanvas.loadmap.idMap
                    && source.area == LoadMap.Area && (command == 53 || command == -32 || command == -31)
                    && bytes != null && bytes.length <= 8192) {
                try {
                    GateNotice notice = GateNotice.parse(command, bytes); notice.received = System.currentTimeMillis();
                    notice.map = source.id; notice.area = source.area; notice.actor = actor(); notice.actorLevel = level();
                    notice.exitX = source.exits[selected].x; notice.exitY = source.exits[selected].y; notice.exitName = source.exits[selected].name;
                    if (notice.levelDenied()) latestNotice = notice;
                } catch (Throwable ignored) {} // Unrelated/malformed notices must not affect map delivery.
            }
            if (command == 41 && bytes != null && bytes.length >= 2 && GameScreen.player != null) {
                int victim = (short)(((bytes[0] & 255) << 8) | (bytes[1] & 255));
                if (victim == GameScreen.player.ID) death = true;
            }
            if (command != 12) return;
            int sequence = ++receivedMapSequence;
            if (bytes == null || bytes.length > 65535) { captureRejected++; status = "Map packet exceeds capture limit"; return; }
            byte[] copy = new byte[bytes.length]; System.arraycopy(bytes, 0, copy, 0, bytes.length);
            if (incoming != null) { captureDrops++; status = "Capture queue overrun: latest map retained"; }
            incomingAttempt = -1; incomingNearExit = false;
            int token = attemptSerial, exitIndex = selected; MapPacket navigation = source;
            if (active && navigation != null && token == attemptSerial && exitIndex >= 0 && exitIndex < navigation.exits.length
                    && GameScreen.player != null && GameCanvas.loadmap.idMap == navigation.id && LoadMap.Area == navigation.area) {
                incomingAttempt = token;
                MapPacket.Exit exit = navigation.exits[exitIndex];
                incomingNearExit = phase == 2 || NavLogic.abs(GameScreen.player.x - exit.x) + NavLogic.abs(GameScreen.player.y - exit.y) < 96;
            }
            incomingSequence = sequence; incomingServer = server(); incoming = copy;
        } catch (Throwable error) { captureRejected++; status = "Capture disabled: " + error.toString(); }
    }
    private static synchronized byte[] take() {
        byte[] value = incoming;
        if (value != null) {
            pendingServer = incomingServer; pendingAttempt = incomingAttempt; pendingNearExit = incomingNearExit;
            pendingSequence = incomingSequence;
        }
        incoming = null; return value;
    }
    /** Same-map IDs cannot signal readiness; wait for native command handling. */
    public static void handled(net.Message message) {
        if (message.command == 12) processedMapSequence = receivedMapSequence;
    }
    public static void manual() {
        synchronized (INPUT_LOCK) { stopped = true; requestedExit = -1; release(); }
    }
    public static void visible(boolean value) { shown = value; if (!value) manual(); }
    public static boolean pointer(int x, int y) {
        try {
            int left = GameCanvas.w / 2 - 22;
            if (x >= left && x < left + 44 && y >= 0 && y < 22) {
                manual(); NavPanel.open(); return true;
            }
            manual();
        } catch (Throwable error) { status = "Panel error: " + error.toString(); }
        return false;
    }
    public static void request(MapPacket map, int exit, boolean continuous) {
        requestedMap = map; requestedContinue = continuous; requestedAt = System.currentTimeMillis(); requestedExit = exit; stopped = false;
    }
    public static void requestSurvey(MapPacket map) {
        requestedMap = map; requestedContinue = true; requestedAt = System.currentTimeMillis(); requestedExit = -2; stopped = false;
    }
    public static void retryBlocked() { manual(); retryRequested = true; status = "Blocked-exit retry requested"; }
    public static void markNpc(MapPacket map, String note, int minimum) throws Exception {
        if (map == null || map != current || !captureReady() || currentServer == null || !currentServer.equals(server()))
            throw new Exception("Map changed; reopen NAV before arming the transfer");
        manual(); npcSource = map; npcServer = server(); npcNote = note; npcMinimum = minimum;
        npcCharacter = GameScreen.player == null ? -1 : GameScreen.player.ID;
        npcUntil = System.currentTimeMillis() + 120000; npcArmed = true;
        status = "NPC mark armed: use the NPC manually within 2 minutes";
    }
    public static void cancelNpc() { npcArmed = false; npcSource = null; status = "NPC mark cancelled"; }
    private static String actor() { return GameScreen.player == null ? "" : GameScreen.player.name; }
    private static int level() { return GameScreen.player == null ? 0 : GameScreen.player.Lv; }
    public static boolean running() { return active; }
    public static void clearRecordings() { manual(); clearRequested = true; }
    public static void exportTo(String directory) { manual(); exportRequested = directory; status = "Export requested"; }
    public static String server() {
        if (LoginScreen.ip != null) return LoginScreen.ip + ":19129";
        int index = GameCanvas.IndexServer;
        if (index >= 0 && index < mSystem.listServer.length && index < GameCanvas.portServer.length)
            return mSystem.listServer[index][1] + ":" + GameCanvas.portServer[index];
        return "unknown";
    }
    private static boolean ready() {
        return GameCanvas.currentScreen == GameCanvas.game && LoadMapScreen.isNextMap
            && SelectCharScreen.isSelectOk && GameScreen.player != null && GameScreen.player.hp > 0
            && GameCanvas.loadmap != null && GameCanvas.loadmap.mapType != null && CLib.Session_ME.gI().isConnected();
    }
    private static boolean captureReady() {
        return LoadMapScreen.isNextMap && GameCanvas.loadmap != null && GameCanvas.loadmap.mapType != null
            && GameScreen.player != null && CLib.Session_ME.gI().isConnected();
    }
    private static void release() {
        synchronized (INPUT_LOCK) {
            if (ownedKey >= 0 && GameCanvas.keyMyHold != null) GameCanvas.keyMyHold[ownedKey] = false;
            ownedKey = -1;
        }
    }
    private static void motionStop() {
        release(); path = null;
        if (GameScreen.player != null) {
            // Never cancel the original server-correction/forced movement path.
            if (Player.isLockKey || !Player.isSendMove || GameScreen.player.isMoveOut) return;
            GameScreen.player.vx = 0; GameScreen.player.vy = 0;
            GameScreen.player.posTransRoad = null;
            GameScreen.player.toX = GameScreen.player.x; GameScreen.player.toY = GameScreen.player.y;
            if (GameScreen.player.Action == 1) GameScreen.player.Action = 0;
        }
    }
    private static void halt(String reason) {
        if (active || ownedKey >= 0) motionStop();
        active = false; resumeOnReady = false; source = null; requestedExit = -1; status = reason;
    }
    private static void hold(int direction) {
        synchronized (INPUT_LOCK) {
            release();
            if (direction >= 0 && !stopped && shown && active) {
                ownedKey = KEYS[direction]; GameCanvas.keyMyHold[ownedKey] = true;
            }
        }
    }
    private static void begin(int exit, boolean continuous) throws Exception {
        MapPacket map = current;
        if (!ready() || !stored || map == null || exit < 0 || exit >= map.exits.length)
            throw new Exception("Wait for a recorded, playable map");
        if (map.id != GameCanvas.loadmap.idMap || map.area != LoadMap.Area
                || map.width != GameCanvas.loadmap.mapW || map.height != GameCanvas.loadmap.mapH
                || currentServer == null || !currentServer.equals(server()))
            throw new Exception("Live map differs from capture; wait for recording");
        if (GameScreen.player.Action != 0 && GameScreen.player.Action != 1)
            throw new Exception("Wait until character is idle");
        if (GameScreen.player.isSelling()) throw new Exception("Close your player store first");
        outward = NavLogic.outward(map, map.exits[exit]);
        if (outward < 0) throw new Exception("Interior/NPC transport needs manual action");
        int goal = NavLogic.approach(map, GameCanvas.loadmap.mapType, map.exits[exit]);
        if (goal < 0) throw new Exception("No walkable approach to this exit");
        motionStop(); GameCanvas.clearAll(); GameScreen.player.resetMove();
        source = map; selected = exit; keepGoing = continuous;
        sourceServer = server(); character = GameScreen.player.ID;
        goalX = goal >> 8; goalY = goal & 255; phase = 1; replans = 0; replanAfterYield = false;
        started = progressAt = System.currentTimeMillis(); crossingAt = 0;
        progressX = GameScreen.player.x; progressY = GameScreen.player.y;
        if (Player.isAutoFire == 1) Player.setCurAutoFire();
        attemptSerial++; active = true; resumeOnReady = false; plan(); status = "Walking to " + map.exits[exit].name;
    }
    private static void plan() throws Exception {
        Player p = GameScreen.player; pathIndex = 0;
        int sx = p.x / 24, sy = p.y / 24;
        if (NavLogic.abs(sx - goalX) + NavLogic.abs(sy - goalY) <= 1) { path = null; return; }
        short[] candidate = GameCanvas.game.updateFindRoad(goalX, goalY, sx, sy, 100);
        if (!NavLogic.validPath(candidate, sx, sy, goalX, goalY, current.width, current.height,
                                GameCanvas.loadmap.mapType, 100))
            throw new Exception("Native pathfinder has no valid route (max 100 steps)");
        path = candidate;
    }
    private static boolean canSchedule() {
        return ready() && stored && current != null && GameCanvas.currentDialog == null && GameCanvas.subDialog == null
            && current.id == GameCanvas.loadmap.idMap && current.area == LoadMap.Area
            && currentServer != null && currentServer.equals(server())
            && !GameCanvas.menu2.isShowMenu && !ChatTextField.isShow && GameScreen.player.currentQuest == null
            && (GameScreen.player.Action == 0 || GameScreen.player.Action == 1)
            && !Player.isLockKey && Player.isSendMove && GameScreen.player.Canmove() && !GameScreen.player.isBinded
            && !GameScreen.player.isSelling() && !GameScreen.player.isMoveOut && !GameScreen.player.isTanHinh;
    }
    private static void blockExit(MapPacket map, int exit, String reason, boolean continueSurvey) throws Exception {
        NavStore.block(server(), map, exit, actor(), level(), reason);
        motionStop(); active = false; source = null; requestedExit = -1;
        resumeOnReady = continueSurvey; keepGoing = continueSurvey; started = System.currentTimeMillis();
        status = "Skipped exit at Lv " + level() + ": " + reason;
    }
    private static void failExit(String reason) throws Exception {
        if (source == null) { halt(reason); return; }
        blockExit(source, selected, reason, keepGoing);
    }
    private static void surveyStep() throws Exception {
        NavGraph.Step step = NavStore.graph.next(server(), current, actor(), level());
        if (step == null) {
            halt("No reachable untried walking exits at Lv " + level() + "; NPC/blocked/disconnected routes may remain"); return;
        }
        try {
            begin(step.exit, true);
            if (step.backtracking) status = "Backtracking toward frontier map " + step.targetMap;
        } catch (Exception error) {
            if (!captureReady() || current.id != GameCanvas.loadmap.idMap || current.area != LoadMap.Area)
                halt("Map changed during route planning; wait for capture and restart");
            else blockExit(current, step.exit, "Walk attempt failed: " + error.toString(), true);
        }
    }
    public static void tick() {
        try {
            release();
            if (death) { death = false; npcArmed = false; stopped = false; halt("Navigation stopped: player died"); }
            if (stopped) {
                stopped = false;
                if (active || resumeOnReady) halt("Paused by user / visibility change");
            }
            if (npcArmed && System.currentTimeMillis() > npcUntil) { npcArmed = false; npcSource = null; }
            if (retryRequested && !NavStore.exporting) {
                retryRequested = false; NavStore.retryBlocks(server(), actor()); status = "Blocked exits cleared for this character";
                NavPanel.refreshLater();
            }
            if (exportRequested != null && !NavStore.exporting) {
                String folder = exportRequested; exportRequested = null; halt("Exporting maps"); NavExport.start(folder);
            }
            if (clearRequested && !NavStore.exporting) {
                clearRequested = false; halt("Recordings cleared"); NavStore.clear(); stored = false;
                if (current != null && ready() && server().equals(currentServer)
                        && current.id == GameCanvas.loadmap.idMap && current.area == LoadMap.Area) {
                    pending = current; pendingServer = currentServer; pendingAt = System.currentTimeMillis();
                }
                NavPanel.refreshLater();
            }
            byte[] bytes = take();
            if (bytes != null) {
                stored = false;
                if (pending != null) captureDrops++;
                try { pending = MapPacket.parse(bytes); }
                catch (java.io.IOException error) { captureRejected++; pending = null; throw error; }
                pendingAt = System.currentTimeMillis();
            }
            if (pending != null && pendingSequence == processedMapSequence && !NavStore.exporting && captureReady() && pendingServer.equals(server())
                    && GameCanvas.loadmap.idMap == pending.id && GameCanvas.loadmap.mapW == pending.width
                    && GameCanvas.loadmap.mapH == pending.height && LoadMap.Area == pending.area) {
                MapPacket map = pending; pending = null;
                int[] collision = GameCanvas.loadmap.mapType;
                if (collision.length != map.width * map.height) throw new Exception("Collision dimensions mismatch");
                stored = false; NavStore.map(pendingServer, map, collision); stored = true;
                MapPacket previous = current; String previousServer = currentServer;
                current = map; currentServer = pendingServer;
                if (active && source != null && sourceServer.equals(server()) && pendingAttempt == attemptSerial
                        && NavLogic.exitArrival(source, map, pendingNearExit)) {
                    NavStore.edge(server(), source, selected, map);
                    boolean resume = keepGoing; motionStop(); active = false; source = null;
                    if (resume) {
                        resumeOnReady = true; status = "Transport recorded; waiting for world ready";
                    } else halt("Transport recorded; navigation stopped");
                } else {
                    if (previous != null && pendingServer.equals(previousServer)
                            && (previous.id != map.id || previous.area != map.area)) {
                        boolean marked = npcArmed && npcSource != null && npcServer.equals(pendingServer)
                            && npcSource.id == previous.id && npcSource.area == previous.area
                            && GameScreen.player.ID == npcCharacter && System.currentTimeMillis() <= npcUntil;
                        NavStore.external(pendingServer, previous, map, marked ? "npc" : "unclassified", marked ? npcNote : "Manual/forced transfer; not an automatic walking link", level(), marked ? npcMinimum : 0);
                        if (marked) { npcArmed = false; npcSource = null; }
                    }
                    status = "Recorded map " + map.id + " (area " + (map.area + 1) + ")";
                }
            }
            if (pending != null && System.currentTimeMillis() - pendingAt > 20000) {
                pending = null; captureRejected++; halt("Capture not committed: map never became ready");
            }
            if (requestedExit != -1 && shown && !NavStore.exporting) {
                if (requestedMap != current) throw new Exception("Map changed; reopen the panel");
                if (current != null && captureReady() && (current.id != GameCanvas.loadmap.idMap || current.area != LoadMap.Area))
                    throw new Exception("Map/area needs a fresh boundary capture before surveying");
                if (Player.isAutoFire == 1) Player.setCurAutoFire();
                if (canSchedule()) {
                    int exit = requestedExit; requestedExit = -1;
                    surveyServer = server(); surveyActor = actor();
                    if (exit == -2) surveyStep(); else begin(exit, requestedContinue);
                } else if (System.currentTimeMillis() - requestedAt > 20000) halt("Start timed out waiting for playable/idle controls");
                else status = "Waiting for playable/idle controls before navigation";
            }
            if (resumeOnReady && shown && !NavStore.exporting) {
                if (surveyServer != null && (!surveyServer.equals(server()) || !surveyActor.equals(actor()))) {
                    halt("Survey stopped: character/server changed");
                } else if (canSchedule()) {
                    surveyStep();
                } else if (System.currentTimeMillis() - started > 60000) halt("World-ready wait timed out");
            }
            if (!active) return;
            if (!shown || NavStore.exporting) { halt("Paused: display hidden/exporting"); return; }
            long now = System.currentTimeMillis();
            if (!ready()) {
                motionStop();
                if (now - started > 60000) halt("Timed out waiting for playable map");
                return;
            }
            if (current.id != GameCanvas.loadmap.idMap || current.area != LoadMap.Area) {
                release(); status = "Waiting for matching map capture"; return;
            }
            Player p = GameScreen.player;
            if (p.ID != character || !server().equals(sourceServer)) { halt("Character/server changed"); return; }
            GateNotice notice = latestNotice;
            if (notice != null && notice.matches(source.id, source.area, source.exits[selected], actor(), level(), now) && notice.levelDenied()
                    && (phase == 2 || NavLogic.abs(p.x - source.exits[selected].x) + NavLogic.abs(p.y - source.exits[selected].y) < 96)) {
                release();
                if (now - notice.received < 150) { status = "Checking server level restriction"; return; }
                latestNotice = null;
                if (notice.command == -32) GameCanvas.end_Dialog();
                failExit("Server level restriction: " + notice.text); return;
            }
            if (GameCanvas.currentDialog != null || GameCanvas.subDialog != null || GameCanvas.menu2.isShowMenu
                    || ChatTextField.isShow || p.currentQuest != null) { halt("Paused for dialog/menu/chat/tutorial"); return; }
            if (Player.isLockKey || !Player.isSendMove || !p.Canmove() || p.isBinded || p.isMoveOut
                    || p.Action == 2 || p.Action == 3 || p.isTanHinh) {
                release(); path = null; replanAfterYield = true; status = "Waiting for movement control";
                if (now - started > 60000) halt("Movement unavailable");
                return;
            }
            if (replanAfterYield && phase == 1) { plan(); replanAfterYield = false; progressAt = now; }
            if (now - started > 60000) { failExit("Walking attempt exceeded 60 seconds; requirement unknown"); return; }
            if (NavLogic.abs(p.x - progressX) + NavLogic.abs(p.y - progressY) >= 6) {
                progressX = p.x; progressY = p.y; progressAt = now;
            }
            if (phase == 1) {
                if (path != null) {
                    while (pathIndex < path.length) {
                        int x = ((path[pathIndex] >> 8) & 255) * 24 + 12;
                        int y = (path[pathIndex] & 255) * 24 + 12;
                        int direction = NavLogic.toward(p.x, p.y, x, y);
                        if (direction >= 0) { hold(direction); break; }
                        pathIndex++;
                    }
                    if (pathIndex >= path.length) path = null;
                } else {
                    int direction = NavLogic.toward(p.x, p.y, goalX * 24 + 12, goalY * 24 + 12);
                    if (direction >= 0) hold(direction);
                    else { phase = 2; crossingAt = progressAt = now; status = "Crossing exit; waiting for server"; }
                }
                if (now - progressAt > 2500) {
                    motionStop(); if (++replans > 2) { failExit("No movement progress; obstacle/requirement unknown"); return; }
                    try { plan(); } catch (Exception error) { failExit("Native route unavailable: " + error.toString()); return; }
                    progressAt = now;
                }
            } else {
                MapPacket.Exit exit = source.exits[selected]; int direction = outward;
                if (outward < 2 && NavLogic.abs(p.y - exit.y) > 8) direction = p.y < exit.y ? 3 : 2;
                if (outward >= 2 && NavLogic.abs(p.x - exit.x) > 8) direction = p.x < exit.x ? 1 : 0;
                hold(direction);
                if (now - crossingAt > 12000 || now - progressAt > 5000)
                    failExit("Exit did not trigger; level/NPC/other requirement unknown");
            }
        } catch (Throwable error) { halt("NAV: " + error.toString()); }
    }
    public static void paint(Graphics g) {
        try {
            int w = GameCanvas.w, h = GameCanvas.h;
            g.translate(-g.getTranslateX(), -g.getTranslateY()); g.setClip(0, 0, w, h);
            g.setColor(0x102b36); g.fillRect(w / 2 - 22, 0, 44, 20);
            g.setColor(0xffffff); g.drawString("NAV", w / 2, 1, Graphics.TOP | Graphics.HCENTER);
            if (active) {
                g.setColor(0); g.fillRect(0, h - 29, w, 15); g.setColor(0xffff66);
                String text = status; if (text.length() > 36) text = text.substring(0, 36);
                g.drawString(text, 2, h - 29, Graphics.TOP | Graphics.LEFT);
            }
        } catch (Throwable ignored) {}
    }
}
