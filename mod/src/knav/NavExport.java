package knav;

import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/** Optional JSR-75 adapter, loaded only when Export is selected. */
public final class NavExport implements Runnable {
    private static final class Counted extends OutputStream {
        private OutputStream output;
        public int count;
        public Counted(OutputStream output) { this.output = output; }
        public void write(int value) throws IOException { output.write(value); count++; }
        public void write(byte[] bytes, int offset, int length) throws IOException { output.write(bytes, offset, length); count += length; }
        public void flush() throws IOException { output.flush(); }
        public void close() throws IOException { output.close(); }
    }
    private String directory;
    private NavExport(String directory) { this.directory = directory; }
    public static void start(String directory) {
        if (NavStore.exporting) { NavMod.status = "Export already running"; return; }
        if (directory == null || !directory.startsWith("file:///") || !directory.endsWith("/")) {
            NavMod.status = "Use a directory URL such as file:///E:/"; return;
        }
        NavStore.exporting = true;
        new Thread(new NavExport(directory)).start();
    }
    public void run() {
        FileConnection file = null; OutputStream out = null;
        String name = "knav-" + System.currentTimeMillis() + ".knav";
        String url = directory + name;
        try {
            FileConnection finalPath = (FileConnection)Connector.open(url, Connector.READ);
            try { if (finalPath.exists()) throw new IOException("Export destination already exists"); }
            finally { finalPath.close(); }
            file = (FileConnection)Connector.open(url + ".part", Connector.READ_WRITE);
            if (file.exists()) throw new IOException("Export destination already exists");
            file.create(); Counted counted = new Counted(file.openOutputStream()); out = counted; NavStore.exportTo(out);
            out.close(); out = null; file.rename(name);
            // Some KEmulator versions return silently if host rename fails.
            FileConnection verified = (FileConnection)Connector.open(url, Connector.READ);
            try {
                if (!verified.exists() || verified.fileSize() != counted.count) throw new IOException("Final export rename/size verification failed");
            } finally { verified.close(); }
            NavMod.status = "Exported " + url;
        } catch (Throwable error) { NavMod.status = "Export failed: " + error.toString(); }
        finally {
            try { if (out != null) out.close(); } catch (Exception ignored) {}
            try { if (file != null) file.close(); } catch (Exception ignored) {}
            NavStore.exporting = false; NavPanel.refreshLater();
        }
    }
}
