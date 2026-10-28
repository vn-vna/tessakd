import java.io.*;
import java.nio.file.*;
import knav.*;
import javax.microedition.io.Connector;

public final class ExportTests {
    static void await() throws Exception {
        long until = System.currentTimeMillis() + 10000;
        while (NavStore.exporting && System.currentTimeMillis() < until) Thread.sleep(5);
        assert !NavStore.exporting : "Export worker timed out";
    }
    public static void main(String[] args) throws Exception {
        Path root = Paths.get(args[0]).toAbsolutePath(); Files.createDirectories(root);
        Connector.root = root;
        Files.createDirectories(root.resolve("Map Exports")); Files.createDirectories(root.resolve("Silent Rename"));
        Files.createDirectories(root.resolve("Denied"));
        MapPacket source = CoreTests.map(8), destination = CoreTests.map(99);
        NavStore.map(GraphTests.SERVER, source, new int[80]);
        NavStore.map(GraphTests.SERVER, destination, new int[80]);
        MapPacket walked = CoreTests.map(9); NavStore.edge(GraphTests.SERVER, source, 0, walked);
        NavStore.external(GraphTests.SERVER, source, destination, "npc", "Harbor gate", 104, 101);
        NavStore.block(GraphTests.SERVER, source, 0, "hero", 104, "Level requirement observed");

        NavExport.start("file:///root/Map Exports/"); await();
        assert NavMod.status.startsWith("Exported ") : NavMod.status;
        try (java.util.stream.Stream<Path> files = Files.list(root.resolve("Map Exports"))) {
            Path[] all = files.toArray(Path[]::new);
            assert all.length == 1 && all[0].toString().endsWith(".knav") && Files.size(all[0]) > 100;
        }
        Connector.silentRename = true;
        NavExport.start("file:///root/Silent Rename/"); await();
        assert NavMod.status.startsWith("Export failed:") && NavMod.status.indexOf("verification") >= 0 : NavMod.status;
        try (java.util.stream.Stream<Path> files = Files.list(root.resolve("Silent Rename"))) {
            assert files.allMatch(path -> path.toString().endsWith(".part"));
        }
        Connector.silentRename = false; Connector.denyWrites = true;
        NavExport.start("file:///root/Denied/"); await();
        assert NavMod.status.indexOf("permission denied") >= 0 : NavMod.status;
        Connector.denyWrites = false;
        NavExport.start("C:\\not-a-jsr75-url"); assert !NavStore.exporting && NavMod.status.indexOf("directory URL") >= 0;
        NavStore.exporting = true; NavExport.start("file:///root/Denied/");
        assert NavMod.status.equals("Export already running"); NavStore.exporting = false;
        System.out.println("PASS: real export code with file API test doubles: raw-space paths, completed rename, silent rename failure, denied permissions and concurrent export guard");
    }
}
