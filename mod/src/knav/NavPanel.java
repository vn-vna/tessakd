package knav;

import com.silverknight.TemMidlet;
import javax.microedition.lcdui.*;

public final class NavPanel implements CommandListener, FolderPicker.Listener {
    private static NavPanel instance;
    private Form form;
    private ChoiceGroup exits;
    private TextField directory, npcName, npcMinimum;
    private Form npcForm;
    private static String selectedDirectory;
    private boolean exportAfterPick;
    private StringItem info;
    private MapPacket map;
    private Command back = new Command("Back", Command.BACK, 1);
    private Command start = new Command("Go through exit", Command.OK, 1);
    private Command stop = new Command("Stop", Command.STOP, 2);
    private Command export = new Command("Export maps", Command.SCREEN, 3);
    private Command refresh = new Command("Refresh", Command.SCREEN, 4);
    private Command clear = new Command("Clear recordings", Command.SCREEN, 5);
    private Command confirm = new Command("Clear", Command.OK, 1);
    private Command cancel = new Command("Cancel", Command.CANCEL, 2);
    private Command browse = new Command("Choose export folder", Command.SCREEN, 6);
    private Command directExport = new Command("Export typed URL", Command.SCREEN, 7);
    private Command survey = new Command("Survey remaining maps", Command.OK, 1);
    private Command retry = new Command("Retry blocked exits", Command.SCREEN, 8);
    private Command markNpc = new Command("Mark next NPC transfer", Command.SCREEN, 9);
    private Command armNpc = new Command("Arm NPC transfer", Command.OK, 1);
    private Command cancelMark = new Command("Cancel NPC mark", Command.SCREEN, 10);

    public static void open() {
        if (instance == null) instance = new NavPanel();
        instance.show();
    }
    private void show() {
        map = NavMod.current; form = new Form("NAV Survey");
        info = new StringItem("Status", summary());
        form.append(info);
        if (map != null) form.append(new StringItem("Current map", map.id + " " + map.name + " / area " + (map.area + 1)));
        exits = new ChoiceGroup("Transport point", Choice.POPUP);
        if (map != null) for (int i = 0; i < map.exits.length; i++)
            exits.append(map.exits[i].name + " (" + map.exits[i].x + "," + map.exits[i].y + ")", null);
        if (exits.size() == 0) exits.append("No map exits captured yet", null);
        form.append(exits);
        String folder = selectedDirectory;
        if (folder == null) folder = System.getProperty("fileconn.dir.memorycard");
        if (folder == null || !folder.startsWith("file:///")) folder = "file:///";
        if (!folder.endsWith("/")) folder += "/";
        directory = new TextField("Export directory URL", folder, 200, TextField.ANY); form.append(directory);
        form.append(new StringItem("Usage", "Survey runs until no reachable untried walking transports remain, backtracking when needed. Visited points prevent cycles and same-map loops. Blocked exits are scoped to your character/level. NPC-only worlds need manual entry. Export opens a drive/folder browser."));
        form.addCommand(back); form.addCommand(start); form.addCommand(stop);
        form.addCommand(export); form.addCommand(refresh); form.addCommand(clear); form.addCommand(browse);
        form.addCommand(directExport); form.addCommand(survey); form.addCommand(retry);
        form.addCommand(markNpc); form.addCommand(cancelMark); form.setCommandListener(this);
        Display.getDisplay(TemMidlet.instance).setCurrent(form);
    }
    public void commandAction(Command command, Displayable displayable) {
        if (command == back) { NavMod.manual(); Display.getDisplay(TemMidlet.instance).setCurrent(TemMidlet.temCanvas); }
        else if (command == start) {
            if (map == null || map.exits.length == 0) { NavMod.status = "Enter a map first"; update(); return; }
            Display.getDisplay(TemMidlet.instance).setCurrent(TemMidlet.temCanvas);
            NavMod.request(map, exits.getSelectedIndex(), false);
        } else if (command == stop) { NavMod.manual(); NavMod.status = "Stop requested"; update(); }
        else if (command == export) {
            pick(true);
        } else if (command == browse) pick(false);
        else if (command == directExport) { NavMod.exportTo(directory.getString()); update(); }
        else if (command == survey) {
            try {
                if (map == null) throw new Exception("Enter and record a map first");
                Display.getDisplay(TemMidlet.instance).setCurrent(TemMidlet.temCanvas); NavMod.requestSurvey(map);
            } catch (Exception error) { NavMod.status = error.toString(); Display.getDisplay(TemMidlet.instance).setCurrent(form); update(); }
        } else if (command == retry) { NavMod.retryBlocked(); update(); }
        else if (command == markNpc) {
            npcForm = new Form("Manual NPC world transfer");
            npcName = new TextField("NPC / transfer note", "NPC world transfer", 160, TextField.ANY);
            npcMinimum = new TextField("Known minimum level (0=unknown)", "0", 5, TextField.NUMERIC);
            npcForm.append(npcName); npcForm.append(npcMinimum);
            npcForm.append(new StringItem("Next step", "Arm this mark, then use the NPC manually within 2 minutes. The resulting link is recorded as NPC-only, never used for automatic walking."));
            npcForm.addCommand(armNpc); npcForm.addCommand(cancel); npcForm.setCommandListener(this);
            Display.getDisplay(TemMidlet.instance).setCurrent(npcForm);
        } else if (command == armNpc) {
            try {
                int minimum = Integer.parseInt(npcMinimum.getString());
                if (minimum < 0 || minimum > 32767 || map == null) throw new Exception("Invalid NPC transfer settings");
                NavMod.markNpc(map, npcName.getString(), minimum);
                Display.getDisplay(TemMidlet.instance).setCurrent(TemMidlet.temCanvas);
            } catch (Exception error) { NavMod.status = error.toString(); Display.getDisplay(TemMidlet.instance).setCurrent(form); update(); }
        } else if (command == cancelMark) { NavMod.cancelNpc(); update(); }
        else if (command == refresh) show();
        else if (command == clear) {
            Alert alert = new Alert("Clear NAV cache?", "Export first. This deletes only this mod's recorded maps and edges.", null, AlertType.CONFIRMATION);
            alert.setTimeout(Alert.FOREVER); alert.addCommand(confirm); alert.addCommand(cancel); alert.setCommandListener(this);
            Display.getDisplay(TemMidlet.instance).setCurrent(alert);
        } else if (command == confirm) {
            NavMod.clearRecordings(); Display.getDisplay(TemMidlet.instance).setCurrent(form);
        } else if (command == cancel) Display.getDisplay(TemMidlet.instance).setCurrent(form);
    }
    private void update() {
        if (info != null) info.setText(summary());
    }
    private String summary() {
        return NavMod.status + "\nSaved maps: " + NavStore.mapCount + " edges: " + NavStore.edgeCount
            + "\nBlocked: " + NavStore.blockedCount + " external links: " + NavStore.externalCount
            + "\nCapture gaps: " + NavMod.captureDrops + " rejected: " + NavMod.captureRejected
            + "\nPlatform: " + System.getProperty("microedition.platform");
    }
    private void pick(boolean doExport) {
        exportAfterPick = doExport;
        try { FolderPicker.show(directory.getString(), this); }
        catch (Throwable error) { NavMod.status = "Folder browser unavailable: " + error.toString(); update(); }
    }
    public void selected(String url) {
        if (url.length() > directory.getMaxSize()) {
            NavMod.status = "Selected folder URL is too long";
            Display.getDisplay(TemMidlet.instance).setCurrent(form); update(); return;
        }
        selectedDirectory = url; directory.setString(url); Display.getDisplay(TemMidlet.instance).setCurrent(form);
        if (exportAfterPick) NavMod.exportTo(url);
        update();
    }
    public void cancelled() { Display.getDisplay(TemMidlet.instance).setCurrent(form); }
    public static void refreshLater() {
        try {
            Display.getDisplay(TemMidlet.instance).callSerially(new Runnable() {
                public void run() { if (instance != null) instance.update(); }
            });
        } catch (Throwable ignored) {}
    }
}
