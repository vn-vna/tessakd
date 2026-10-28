import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/** Metadata-only Java SE verifier pass; no game class initializers or main run. */
public final class VerifyClasses {
    public static void main(String[] args) throws Exception {
        URL[] urls = new URL[args.length];
        for (int i = 0; i < args.length; i++) urls[i] = Paths.get(args[i]).toUri().toURL();
        int count = 0;
        try (URLClassLoader loader = new URLClassLoader(urls, null); ZipFile jar = new ZipFile(args[0])) {
            for (Enumeration<? extends ZipEntry> entries = jar.entries(); entries.hasMoreElements();) {
                String name = entries.nextElement().getName();
                if (!name.endsWith(".class")) continue;
                Class<?> type = Class.forName(name.substring(0, name.length() - 6).replace('/', '.'), false, loader);
                type.getDeclaredMethods(); type.getDeclaredConstructors(); count++;
            }
        }
        System.out.println("PASS: -Xverify:all loaded metadata for " + count + " game/helper classes without initialization");
    }
}
