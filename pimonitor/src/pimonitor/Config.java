package pimonitor;

import java.util.HashMap;

public class Config {

    private static Config instance;
    private static final String CONFIG_FILE = "config.xml";

    private static boolean debugMode;
    private static String updateInterval;
    private static HashMap<String, TSChannel> channelMap;

    private Config() {
        ConfigFileLoader cfl = new ConfigFileLoader(CONFIG_FILE);

        String debugString = cfl.getConfigValue("debugMode");
        debugMode = Boolean.valueOf(debugString);

        if (debugMode) {
            System.out.println("Debug mode is ON");
        } else {
            System.out.println("Debug mode is OFF");
        }

        updateInterval = cfl.getConfigValue("updateInterval");

        if (Config.getDebugMode()) {
            System.out.println("Update Interval: " + updateInterval + "ms");
        }

        channelMap = cfl.getChannels();

        if (Config.getDebugMode()) {
            for (String key : channelMap.keySet()) {
                String debugMsg = "Loaded Channel with ID "
                        + key
                        + " with channel Key "
                        + channelMap.get(key).getChannelKey()
                        + " and Field #"
                        + channelMap.get(key).getFieldId();

                System.out.println(debugMsg);
            }
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public static boolean getDebugMode() {
        return debugMode;
    }

    public static HashMap<String, TSChannel> getChannelMap() {
        return channelMap;
    }
}
