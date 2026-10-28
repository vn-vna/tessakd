package knav;

import java.io.IOException;

/** Portable JSR-75 URLs, including emulator virtual roots and Nokia drives. */
public final class FolderPaths {
    private static String encode(String name) throws IOException {
        byte[] bytes = name.getBytes("UTF-8"); StringBuffer out = new StringBuffer();
        String hex = "0123456789ABCDEF";
        for (int i = 0; i < bytes.length; i++) {
            int c = bytes[i] & 255;
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9'
                    || c == '-' || c == '_' || c == '.' || c == '~' || c == ':') out.append((char)c);
            else { out.append('%'); out.append(hex.charAt(c >> 4)); out.append(hex.charAt(c & 15)); }
        }
        return out.toString();
    }
    private static String segment(String name) throws IOException {
        if (name.endsWith("/")) name = name.substring(0, name.length() - 1);
        if (name.length() == 0 || name.equals(".") || name.equals("..") || name.indexOf('/') >= 0
                || name.indexOf('\\') >= 0) throw new IOException("Invalid directory name");
        return encode(name) + "/";
    }
    public static String childRaw(String parent, String name) throws IOException {
        // Validate exactly as for normal URLs, but preserve raw names for
        // KEmulator's FileConnectionImpl, which does not percent-decode paths.
        segment(name);
        if (!parent.startsWith("file:///") || !parent.endsWith("/")) throw new IOException("Invalid parent URL");
        return parent + (name.endsWith("/") ? name : name + "/");
    }
    public static String unescape(String value) throws IOException {
        byte[] bytes = value.getBytes("UTF-8"); java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        String hex = "0123456789abcdef";
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == '%' && i + 2 < bytes.length) {
                int a = hex.indexOf(Character.toLowerCase((char)(bytes[i + 1] & 255)));
                int b = hex.indexOf(Character.toLowerCase((char)(bytes[i + 2] & 255)));
                if (a >= 0 && b >= 0) { out.write((a << 4) | b); i += 2; continue; }
            }
            out.write(bytes[i] & 255);
        }
        return new String(out.toByteArray(), "UTF-8");
    }
    public static String root(String name) throws IOException { return "file:///" + segment(name); }
    public static String child(String parent, String name) throws IOException {
        if (!parent.startsWith("file:///") || !parent.endsWith("/")) throw new IOException("Invalid parent URL");
        return parent + segment(name);
    }
    public static String parent(String url) {
        if (url == null || !url.startsWith("file:///") || url.equals("file:///")) return null;
        int end = url.length() - (url.endsWith("/") ? 2 : 1);
        int slash = url.lastIndexOf('/', end);
        return slash < 8 ? null : url.substring(0, slash + 1);
    }
}
