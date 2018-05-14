package pimonitor;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Class to manage all the ThingSpeak-Channels and generate the strings for the
 * HTTPGetter to process. Sorry for the confusing String-handling stuff in the
 * send()-Method.
 *
 * @author st3inbeiss
 */
public class ChannelManager extends TimerTask {

    private final ArrayList<ChannelInterface> channelList;
    private final HTTPGetter httpGetter;

    public ChannelManager() {
        channelList = new ArrayList();
        httpGetter = new HTTPGetter();
    }

    /**
     * Adds a channel to its list which is being processed when send() is
     * called.
     *
     * @param channel The channel, obviously.
     */
    public void registerChannel(ChannelInterface channel) {
        channelList.add(channel);
    }

    /**
     * Generates the URL with the GET-Parameters and hands them over to the
     * HTTPGetter.
     */
    public void sendValues() {
        ArrayList<String> GETStrings = new ArrayList();

        channelList.forEach((ci) -> {
            // If this is the first value for a certain key, this variable
            // will stay false.
            boolean added = false;
            // Search for the current key in all the currently available
            // GET-Strings and add the value to the String with the
            // corersponding key.
            for (String GETString : GETStrings) {
                if (GETString.startsWith(ci.getChannel().getChannelKey())) {
                    String fieldId = ci.getChannel().getFieldId();
                    String value = ci.getValue();
                    String newGETString = GETString + "&field" + fieldId + "=" + value;
                    GETStrings.remove(GETString);
                    GETStrings.add(newGETString);
                    // If the value has been added, make sure to not add it
                    // again below.
                    added = true;
                }
            }

            // If it's really the first value for a key, add a new GET-String.
            if (!added) {
                String apiKey = ci.getChannel().getChannelKey();
                String fieldId = ci.getChannel().getFieldId();
                String value = ci.getValue();
                String newGETString = apiKey + "&field" + fieldId + "=" + value;
                GETStrings.add(newGETString);
            }
        });

        // Hand all the GET-Strings over to the HTTPGetter.
        GETStrings.forEach((GETString) -> {
            httpGetter.sendData("https://api.thingspeak.com/update?api_key=" + GETString);
        });
    }

    /**
     * What happens, when the task is scheduled.
     */
    @Override
    public void run() {
        sendValues();
    }
}
