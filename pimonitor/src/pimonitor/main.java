package pimonitor;

public class main {

    public static void main(String[] args) {
        Config.getInstance();
        ChannelManager mgr = new ChannelManager();

        // Register channels which have to send data.
        mgr.registerChannel(new ReaderCPU());
        mgr.registerChannel(new ReaderRandom());

        try {
            while (true) {
                mgr.send();
                Thread.sleep(Config.getUpdateInterval());
            }
        } catch (InterruptedException e) {
            System.out.println("Threads interrupted.");
        }
    }
}
