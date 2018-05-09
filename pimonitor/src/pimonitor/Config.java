package pimonitor;

import java.util.HashMap;

/**
 * Singleton for managing the config which is read from a file.
 *
 * @author st3inbeiss
 */
public class Config {

    private static Config instance;
    private static final String CONFIG_FILE = "config.xml";
    private static boolean debugMode;
    private static long updateInterval;
    private static HashMap<String, TSChannel> channelMap;

    /**
     * Initalizes the config and prints some debug messages even if debug is
     * deactivated.
     */
    private Config() {
        ConfigFileLoader cfl = new ConfigFileLoader(CONFIG_FILE);

        String debugString = cfl.getConfigValue("debugMode");
        debugMode = Boolean.valueOf(debugString);

        if (debugMode) {
            System.out.println("Debug mode is ON");
        } else {
            System.out.println("Debug mode is OFF");
        }

        updateInterval = Long.valueOf(cfl.getConfigValue("updateInterval"));

        if (Config.isDebugMode()) {
            System.out.println("Update Interval: " + updateInterval + "ms");
        }

        channelMap = cfl.getChannels();

        if (Config.isDebugMode()) {
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

    /**
     * Singleton-Pattern.
     *
     * @return The Instance!
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * The value of the debug-mode from the config-file.
     *
     * @return true if debug-mode is enabled, false if not.
     */
    public static boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Returns all the channels which are in the config-file.
     *
     * @return HashMap of all the channels where k is the ID of the channel and
     * v is the TSChannel-Object where the attributes of the cahannel are
     * stored.
     */
    public static HashMap<String, TSChannel> getChannelMap() {
        return channelMap;
    }

    /**
     * The value of the update-interval from the config-file.
     *
     * @return The update-interval in milliseconds.
     */
    public static long getUpdateInterval() {
        return updateInterval;
    }
}
