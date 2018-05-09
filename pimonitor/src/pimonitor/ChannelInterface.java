package pimonitor;

/**
 * Interface for Thingspeak-Channels. Each Reader (or Channel) that wants to
 * register with the ChannelManager has to implement this.
 *
 * @author st3inbeiss
 */
public interface ChannelInterface {

    /**
     * Gets the Value which is then further processed to Thingspeak.
     *
     * @return String of the Value which gets sent to Thingspeak.
     */
    public String getValue();

    /**
     * Gets the channel-configuration (Channel key, field ID, ...).
     *
     * @return TSChannel Object which contains the channel-configuration.
     */
    public TSChannel getChannel();

}
