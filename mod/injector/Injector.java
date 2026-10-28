import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/** Exact-profile transformer. Desktop only; never copied into the phone JAR. */
public final class Injector implements Opcodes {
    static MethodNode method(ClassNode c, String name, String desc) {
        MethodNode found = null;
        for (MethodNode m : c.methods) if (m.name.equals(name) && m.desc.equals(desc)) {
            if (found != null) throw new IllegalStateException("Ambiguous method " + name);
            found = m;
        }
        if (found == null) throw new IllegalStateException("Missing " + c.name + "." + name + desc);
        return found;
    }
    static MethodInsnNode call(String name, String desc) {
        return new MethodInsnNode(INVOKESTATIC, "knav/NavMod", name, desc, false);
    }
    static AbstractInsnNode previous(AbstractInsnNode n) {
        do { n = n.getPrevious(); } while (n != null && n.getOpcode() < 0);
        return n;
    }
    static ClassNode read(byte[] data) {
        ClassNode c = new ClassNode();
        new ClassReader(data).accept(c, ClassReader.SKIP_FRAMES);
        return c;
    }
    static byte[] patch(String path, byte[] data) {
        ClassNode c = read(data);
        if (c.version != V1_1) throw new IllegalStateException("Unexpected target version " + c.version);
        if (path.equals("Main/GameCanvas.class")) {
            method(c, "update", "()V").instructions.insert(call("tick", "()V"));
            MethodNode paint = method(c, "paint", "(LCLib/TemGraphics;)V");
            int count = 0;
            for (AbstractInsnNode n : paint.instructions.toArray()) if (n.getOpcode() == RETURN) {
                InsnList code = new InsnList();
                code.add(new VarInsnNode(ALOAD, 1));
                code.add(new FieldInsnNode(GETFIELD, "CLib/TemGraphics", "g", "Ljavax/microedition/lcdui/Graphics;"));
                code.add(call("paint", "(Ljavax/microedition/lcdui/Graphics;)V"));
                paint.instructions.insertBefore(n, code); count++;
            }
            if (count != 1) throw new IllegalStateException("Unexpected paint returns " + count);
        } else if (path.equals("com/silverknight/TemCanvas.class")) {
            MethodNode pointer = method(c, "pointerPressed", "(II)V");
            InsnList guard = new InsnList(); LabelNode pass = new LabelNode();
            guard.add(new VarInsnNode(ILOAD, 1)); guard.add(new VarInsnNode(ILOAD, 2));
            guard.add(call("pointer", "(II)Z")); guard.add(new JumpInsnNode(IFEQ, pass));
            guard.add(new InsnNode(RETURN)); guard.add(pass); pointer.instructions.insert(guard);
            method(c, "keyPressed", "(I)V").instructions.insert(call("manual", "()V"));
            for (String name : new String[]{"showNotify", "hideNotify"}) {
                for (MethodNode existing : c.methods) if (existing.name.equals(name))
                    throw new IllegalStateException("Unexpected visibility override");
                MethodNode m = new MethodNode(ACC_PROTECTED, name, "()V", null, null);
                m.instructions.add(new InsnNode(name.equals("showNotify") ? ICONST_1 : ICONST_0));
                m.instructions.add(call("visible", "(Z)V"));
                m.instructions.add(new VarInsnNode(ALOAD, 0));
                m.instructions.add(new MethodInsnNode(INVOKESPECIAL, "javax/microedition/lcdui/Canvas", name, "()V", false));
                m.instructions.add(new InsnNode(RETURN)); c.methods.add(m);
            }
        } else if (path.equals("CLib/Session_ME$MessageCollector.class")) {
            MethodNode m = method(c, "readMessage", "()Lnet/Message;");
            int count = 0;
            for (AbstractInsnNode n : m.instructions.toArray()) {
                if (!(n instanceof MethodInsnNode)) continue;
                MethodInsnNode init = (MethodInsnNode)n;
                if (!init.owner.equals("net/Message") || !init.name.equals("<init>") || !init.desc.equals("(B[B)V")) continue;
                AbstractInsnNode array = previous(n), command = previous(array), dup = previous(command), alloc = previous(dup);
                if (!(array instanceof VarInsnNode) || array.getOpcode() != ALOAD || ((VarInsnNode)array).var != 3
                        || !(command instanceof VarInsnNode) || command.getOpcode() != ILOAD || ((VarInsnNode)command).var != 1
                        || dup.getOpcode() != DUP || !(alloc instanceof TypeInsnNode) || alloc.getOpcode() != NEW
                        || !((TypeInsnNode)alloc).desc.equals("net/Message"))
                    throw new IllegalStateException("Decoded-message anchor mismatch");
                InsnList capture = new InsnList(); capture.add(new VarInsnNode(ILOAD, 1));
                capture.add(new VarInsnNode(ALOAD, 3)); capture.add(call("packet", "(B[B)V"));
                m.instructions.insertBefore(alloc, capture); count++;
            }
            if (count != 1) throw new IllegalStateException("Expected one decoded packet anchor");
        } else if (path.equals("netcommand/global/GlobalMessageHandler.class")) {
            MethodNode m = method(c, "onMessage", "(Lnet/Message;)V"); int count = 0;
            for (AbstractInsnNode n : m.instructions.toArray()) if (n.getOpcode() == RETURN) {
                InsnList done = new InsnList(); done.add(new VarInsnNode(ALOAD, 1));
                done.add(call("handled", "(Lnet/Message;)V")); m.instructions.insertBefore(n, done); count++;
            }
            if (count != 1) throw new IllegalStateException("Unexpected dispatcher return count " + count);
        } else {
            throw new IllegalStateException("Unsupported patch target " + path);
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        c.accept(writer); return writer.toByteArray();
    }
    static void put(ZipOutputStream out, String name, byte[] data) throws IOException {
        out.putNextEntry(new ZipEntry(name)); out.write(data); out.closeEntry();
    }
    public static void main(String[] args) throws Exception {
        if (args[0].equals("verify")) {
            Map<String,Integer> calls = new HashMap<>();
            try (ZipFile zip = new ZipFile(args[1])) {
                for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements();) {
                    ZipEntry entry = e.nextElement();
                    if (!entry.getName().endsWith(".class") || entry.getName().startsWith("knav/")) continue;
                    ClassNode c = read(zip.getInputStream(entry).readAllBytes());
                    for (MethodNode m : c.methods) for (AbstractInsnNode n : m.instructions.toArray())
                        if (n instanceof MethodInsnNode && ((MethodInsnNode)n).owner.equals("knav/NavMod")) {
                            String key = ((MethodInsnNode)n).name;
                            calls.put(key, calls.getOrDefault(key, 0) + 1);
                        }
                }
            }
            for (String key : new String[]{"tick", "paint", "pointer", "manual", "packet", "handled"})
                if (calls.getOrDefault(key, 0) != 1) throw new IllegalStateException("Bad hook count " + key + calls);
            if (calls.getOrDefault("visible", 0) != 2) throw new IllegalStateException("Bad visibility hooks");
            System.out.println("Verified hook counts: " + calls); return;
        }
        Set<String> targets = new HashSet<>(Arrays.asList("Main/GameCanvas.class", "com/silverknight/TemCanvas.class", "CLib/Session_ME$MessageCollector.class", "netcommand/global/GlobalMessageHandler.class"));
        try (ZipFile input = new ZipFile(args[1]); ZipOutputStream out = new ZipOutputStream(new FileOutputStream(args[3]))) {
            for (Enumeration<? extends ZipEntry> e = input.entries(); e.hasMoreElements();) {
                String name = e.nextElement().getName();
                if (name.startsWith("knav/") || name.equals("META-INF/KNAV.JSON")) throw new IllegalStateException("Already modded input");
            }
            for (String target : targets) {
                ZipEntry entry = input.getEntry(target);
                if (entry == null) throw new IllegalStateException("Missing target " + target);
                put(out, target, patch(target, input.getInputStream(entry).readAllBytes()));
            }
            Path root = Paths.get(args[2]);
            try (java.util.stream.Stream<Path> files = Files.walk(root)) {
                for (Path file : files.filter(p -> p.toString().endsWith(".class")).sorted().toList())
                    put(out, root.relativize(file).toString().replace('\\','/'), Files.readAllBytes(file));
            }
        }
        System.out.println("Patched exactly four original classes");
    }
}
