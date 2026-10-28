package knav;
public final class NavPanel {
    public static volatile int refreshes;
    public static void refreshLater() { refreshes++; }
}
