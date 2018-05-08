package pimonitor;

import java.util.ArrayList;

public class ChannelManager {

    private final ArrayList<ChannelInterface> channelList;
    private final ThingspeakSender tSender;

    public ChannelManager() {
        channelList = new ArrayList();
        tSender = new ThingspeakSender();
    }

    public void registerChannel(ChannelInterface ci) {
        channelList.add(ci);
    }

    public void send() {
        ArrayList<String> GETStrings = new ArrayList();

        channelList.forEach((ci) -> {
            boolean added = false;
            for (String GETString : GETStrings) {
                if (GETString.startsWith(ci.getChannel().getChannelKey())) {
                    String fieldId = ci.getChannel().getFieldId();
                    String value = ci.getValue();
                    String newGETString = GETString + "&field" + fieldId + "=" + value;
                    GETStrings.remove(GETString);
                    GETStrings.add(newGETString);
                    added = true;
                }
            }

            if (!added) {
                String apiKey = ci.getChannel().getChannelKey();
                String fieldId = ci.getChannel().getFieldId();
                String value = ci.getValue();
                String newGETString = apiKey + "&field" + fieldId + "=" + value;
                GETStrings.add(newGETString);
            }
        });

        for (String GETString : GETStrings) {
            tSender.sendData("https://api.thingspeak.com/update?api_key=" + GETString);
        }
    }
}
