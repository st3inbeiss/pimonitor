package pimonitor;

/**
 * Data structure for Thingspeak-Channels.
 *
 * @author st3inbeiss
 */
public class TSChannel {

    private final String channelKey;
    private final String fieldId;

    public TSChannel(String channelKey, String fieldId) {
        this.channelKey = channelKey;
        this.fieldId = fieldId;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public String getFieldId() {
        return fieldId;
    }
}
