package javax.microedition.rms;
public interface RecordEnumeration {
    boolean hasNextElement();
    int nextRecordId();
    byte[] nextRecord();
    int numRecords();
    void destroy();
}
