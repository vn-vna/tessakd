package javax.microedition.io.file;
public interface FileConnection extends javax.microedition.io.Connection {
    boolean exists();
    void create() throws java.io.IOException;
    java.io.OutputStream openOutputStream() throws java.io.IOException;
    void rename(String name) throws java.io.IOException;
    long fileSize() throws java.io.IOException;
}
