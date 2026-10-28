package javax.microedition.io;
import java.io.*;
import java.nio.file.*;
import javax.microedition.io.file.FileConnection;

/** File API test double, confined to a caller-supplied build-test directory. */
public final class Connector {
    public static final int READ = 1, READ_WRITE = 3;
    public static Path root;
    public static boolean silentRename, denyWrites;
    public static Connection open(String url, int mode) throws IOException {
        if (!url.startsWith("file:///root/")) throw new IOException("Unknown test root");
        if (denyWrites && mode == READ_WRITE) throw new SecurityException("File permission denied");
        final Path path = root.resolve(url.substring("file:///root/".length())).normalize();
        if (!path.startsWith(root)) throw new IOException("Test path outside root");
        return new FileConnection() {
            public boolean exists() { return Files.exists(path); }
            public void create() throws IOException { Files.createFile(path); }
            public OutputStream openOutputStream() throws IOException { return Files.newOutputStream(path); }
            public void rename(String name) throws IOException {
                if (!silentRename) Files.move(path, path.resolveSibling(name));
            }
            public long fileSize() throws IOException { return Files.size(path); }
            public void close() {}
        };
    }
}
