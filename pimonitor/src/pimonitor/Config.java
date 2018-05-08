package pimonitor;

public class Config {

    private static Config instance;
    private static final String CONFIG_FILE = "config.xml";

    private static boolean debugMode;
    private static String updateInterval;

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
            System.out.println("Update Interval: " + updateInterval);
        }
        
        cfl.getChannels();
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
}
