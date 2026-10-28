package knav;

import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;
import javax.microedition.lcdui.*;
import com.silverknight.TemMidlet;

/** In-MIDlet folder picker: the same implementation works with JSR-75 on both targets. */
public final class FolderPicker implements CommandListener, Runnable {
    public interface Listener { void selected(String url); void cancelled(); }
    private Listener listener;
    private List list;
    private Vector paths;
    private String current, requested;
    private boolean busy;
    private Command choose = new Command("Use this folder", Command.OK, 1);
    private Command open = new Command("Open", Command.ITEM, 2);
    private Command up = new Command("Up", Command.SCREEN, 3);
    private Command roots = new Command("Drives / roots", Command.SCREEN, 4);
    private Command cancel = new Command("Cancel", Command.CANCEL, 5);

    public static void show(String initial, Listener listener) {
        FolderPicker picker = new FolderPicker(); picker.listener = listener;
        picker.load(initial != null && initial.startsWith("file:///") && !initial.equals("file:///") ? initial : null);
    }
    private void load(String path) {
        if (busy) return;
        busy = true; requested = path;
        list = new List("Reading folders...", List.IMPLICIT); list.addCommand(cancel); list.setCommandListener(this);
        Display.getDisplay(TemMidlet.instance).setCurrent(list); new Thread(this).start();
    }
    public void run() {
        final Vector names = new Vector(), urls = new Vector();
        String path = requested, warning = null; FileConnection directory = null;
        try {
            if (path == null) {
                Enumeration available = FileSystemRegistry.listRoots();
                while (available.hasMoreElements() && names.size() < 64) {
                    String name = (String)available.nextElement(); names.addElement(name); urls.addElement(FolderPaths.root(name));
                }
            } else {
                directory = (FileConnection)Connector.open(path, Connector.READ);
                boolean rawUrls = directory.getClass().getName().equals("javax.microedition.io.file.FileConnectionImpl");
                if (rawUrls && !directory.exists()) {
                    String decoded = FolderPaths.unescape(path);
                    if (!decoded.equals(path)) { directory.close(); directory = (FileConnection)Connector.open(decoded, Connector.READ); }
                }
                if (!directory.exists() || !directory.isDirectory()) throw new java.io.IOException("Directory is unavailable");
                path = directory.getURL(); if (!path.endsWith("/")) path += "/";
                Enumeration entries = directory.list(); int examined = 0;
                if (entries == null) throw new java.io.IOException("Filesystem returned no directory listing");
                while (entries.hasMoreElements() && examined++ < 4096 && names.size() < 512) {
                    String name = (String)entries.nextElement();
                    if (!name.endsWith("/") || name.equals("../") || name.equals("./")) continue;
                    String url = rawUrls ? FolderPaths.childRaw(path, name) : FolderPaths.child(path, name); int index = 0;
                    while (index < names.size() && ((String)names.elementAt(index)).toLowerCase().compareTo(name.toLowerCase()) <= 0) index++;
                    names.insertElementAt(name, index); urls.insertElementAt(url, index);
                }
                if (entries.hasMoreElements()) warning = "Listing limited; type a URL in NAV for unlisted folders";
            }
        } catch (Throwable error) { warning = error.toString(); path = null; }
        finally { try { if (directory != null) directory.close(); } catch (Exception ignored) {} }
        final String selectedPath = path, message = warning;
        Display.getDisplay(TemMidlet.instance).callSerially(new Runnable() {
            public void run() {
                if (listener == null) return;
                current = selectedPath; paths = urls;
                list = new List(current == null ? "Choose drive / virtual root" : "Choose export folder", List.IMPLICIT);
                for (int i = 0; i < names.size(); i++) list.append((String)names.elementAt(i), null);
                if (current != null) { list.addCommand(choose); list.addCommand(up); }
                list.addCommand(open); list.addCommand(roots); list.addCommand(cancel); list.setCommandListener(FolderPicker.this);
                busy = false; Display display = Display.getDisplay(TemMidlet.instance); display.setCurrent(list);
                if (message != null) {
                    Alert alert = new Alert("Folder browser", message, null, AlertType.INFO);
                    alert.setTimeout(Alert.FOREVER); display.setCurrent(alert, list);
                }
            }
        });
    }
    public void commandAction(Command command, Displayable target) {
        if (command == cancel) { Listener callback = listener; listener = null; callback.cancelled(); return; }
        if (busy) return;
        if (command == choose && current != null) { Listener callback = listener; listener = null; callback.selected(current); }
        else if (command == up) load(FolderPaths.parent(current));
        else if (command == roots) load(null);
        else if ((command == open || command == List.SELECT_COMMAND) && paths != null && list.getSelectedIndex() >= 0)
            load((String)paths.elementAt(list.getSelectedIndex()));
    }
}
