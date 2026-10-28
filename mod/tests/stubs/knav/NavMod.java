package knav;
/** Diagnostic fields required by NavStore; no game code is run in storage tests. */
public final class NavMod {
    public static int captureDrops = 2, captureRejected = 1;
    public static volatile String status;
}
