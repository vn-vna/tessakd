import java.io.*;
import knav.*;
import javax.microedition.rms.RecordStore;

public final class StoreTests {
    public static void main(String[] args) throws Exception {
        RecordStore original = RecordStore.openRecordStore("user_pass", true);
        original.addRecord(new byte[]{1,2,3}, 0, 3);
        NavStore.clear();
        MapPacket source = MapPacket.parse(CoreTests.fixture(10, 8, 1));
        NavStore.map("fixture.invalid:19129", source, new int[80]);
        NavStore.map("fixture.invalid:19129", source, new int[80]);
        assert NavStore.mapCount == 1 : "Repeated observation must replace, not duplicate";
        MapPacket next = MapPacket.parse(CoreTests.fixture(10, 8, 1)); next.id = 9;
        NavStore.edge("fixture.invalid:19129", source, 0, next);
        NavStore.edge("fixture.invalid:19129", source, 0, next);
        assert NavStore.edgeCount == 1 && NavStore.traversed("fixture.invalid:19129", source, source.exits[0]);
        MapPacket separate = CoreTests.map(99);
        NavStore.map("fixture.invalid:19129", separate, new int[80]);
        NavStore.external("fixture.invalid:19129", source, separate, "npc", "Harbor gate", 104, 101);
        NavStore.block("fixture.invalid:19129", source, 0, "hero", 104, "Level requirement observed");
        NavGraph.Block legacy = GraphTests.blocked(source, 0, 104); legacy.name = "Old translated label";
        byte[] legacyBytes = CaptureFormat.block(legacy);
        RecordStore.openRecordStore("kn_nav_v1", true).addRecord(legacyBytes, 0, legacyBytes.length);
        assert NavStore.externalCount == 1 && NavStore.blockedCount == 1;
        assert NavStore.graph.next("fixture.invalid:19129", source, "hero", 104) == null;
        // Simulate losing process-local indexes while retaining the backing RMS.
        java.lang.reflect.Method reset = NavStore.class.getDeclaredMethod("resetIndex"); reset.setAccessible(true); reset.invoke(null);
        java.lang.reflect.Field store = NavStore.class.getDeclaredField("store"); store.setAccessible(true); store.set(null, null);
        NavStore.init();
        assert NavStore.mapCount == 2 && NavStore.edgeCount == 1 && NavStore.blockedCount == 1 && NavStore.externalCount == 1;
        NavStore.exporting = true;
        boolean refused = false;
        try { NavStore.clear(); } catch (IOException expected) { refused = true; }
        assert refused;
        NavStore.map("ignored.invalid:1", source, new int[80]);
        assert NavStore.mapCount == 2;
        FileOutputStream file = new FileOutputStream(args[0]); NavStore.exportTo(file); file.close();
        NavStore.exporting = false;
        NavStore.retryBlocks("fixture.invalid:19129", "hero"); assert NavStore.blockedCount == 0;
        reset.invoke(null); store.set(null, null); NavStore.init();
        assert NavStore.blockedCount == 0 : "Retry must remove historical translated records, including after reopening";
        // The cap rejects new maps without silently evicting existing evidence.
        for (int i = 2; i < NavStore.MAX_MAPS; i++) NavStore.map("fixture" + i + ".invalid:1", source, new int[80]);
        refused = false;
        try { NavStore.map("overflow.invalid:1", source, new int[80]); } catch (IOException expected) { refused = true; }
        assert refused && NavStore.mapCount == NavStore.MAX_MAPS;
        NavStore.clear();
        assert NavStore.mapCount == 0 && NavStore.edgeCount == 0;
        assert original.getRecord(1).length == 3 : "Clear must not touch original game data";
        System.out.println("PASS: map replacement, edge tracking, export pause, cache cap, explicit clear and original-RMS isolation (in-memory test double)");
    }
}
