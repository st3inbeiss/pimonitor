package pimonitor;

public class main {

    public static void main(String[] args) {
        // Initialize the Config
        Config.getInstance();
        
        ChannelManager mgr = new ChannelManager();

        Config.getChannelMap().entrySet().forEach((entry) -> {
            String className = entry.getKey();
            try {
                ChannelInterface chan = (ChannelInterface) Class.forName("pimonitor."+className).newInstance();
                mgr.registerChannel(chan);
            } catch (Exception e) {
                System.out.println("Class not found: " + className);
            }
        });

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
