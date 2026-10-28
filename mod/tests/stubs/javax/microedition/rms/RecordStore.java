package javax.microedition.rms;
import java.util.*;

/** In-memory storage test double only. Never packaged in the mod. */
public final class RecordStore {
    private static final Map<String,RecordStore> stores = new HashMap<>();
    private final Map<Integer,byte[]> records = new TreeMap<>();
    private int nextId = 1;
    public static RecordStore openRecordStore(String name, boolean create) throws RecordStoreNotFoundException {
        if (!stores.containsKey(name)) {
            if (!create) throw new RecordStoreNotFoundException();
            stores.put(name, new RecordStore());
        }
        return stores.get(name);
    }
    public static void deleteRecordStore(String name) throws RecordStoreNotFoundException {
        if (stores.remove(name) == null) throw new RecordStoreNotFoundException();
    }
    public int addRecord(byte[] data, int start, int length) {
        int id = nextId++; records.put(id, Arrays.copyOfRange(data, start, start + length)); return id;
    }
    public void setRecord(int id, byte[] data, int start, int length) {
        if (!records.containsKey(id)) throw new IllegalArgumentException("Missing record");
        records.put(id, Arrays.copyOfRange(data, start, start + length));
    }
    public byte[] getRecord(int id) { return records.get(id).clone(); }
    public void deleteRecord(int id) { records.remove(id); }
    public int getSize() { int count = 0; for (byte[] bytes : records.values()) count += bytes.length; return count; }
    public void closeRecordStore() {}
    public RecordEnumeration enumerateRecords(Object filter, Object comparator, boolean keepUpdated) {
        final Integer[] ids = records.keySet().toArray(new Integer[0]);
        return new RecordEnumeration() {
            int cursor;
            public boolean hasNextElement() { return cursor < ids.length; }
            public int nextRecordId() { return ids[cursor++]; }
            public byte[] nextRecord() { return getRecord(nextRecordId()); }
            public int numRecords() { return ids.length; }
            public void destroy() { cursor = ids.length; }
        };
    }
}
