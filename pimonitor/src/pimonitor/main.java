package pimonitor;

import java.util.Timer;

public class main {

    public static void main(String[] args) {
        // Initialize the Config
        Config.getInstance();

        ChannelManager mgr = new ChannelManager();

        Config.getChannelMap().entrySet().forEach((entry) -> {
            String className = entry.getKey();
            try {
                ChannelInterface chan = (ChannelInterface) Class.forName("pimonitor." + className).newInstance();
                mgr.registerChannel(chan);
            } catch (Exception e) {
                System.out.println("Class not found: " + className);
            }
        });

        // Execute the run() of the ChannelManager as configured.
        Timer timer = new Timer();
        timer.schedule(mgr, 0, Config.getUpdateInterval());
    }
}
