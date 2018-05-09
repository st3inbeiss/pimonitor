package pimonitor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Channel to write random numbers to Thingspeak. Makes no sense, I know.
 *
 * @author st3inbeiss
 */
public class ReaderRandom implements ChannelInterface {

    private final TSChannel chan;

    public ReaderRandom() {
        chan = Config.getChannelMap().get("random");
    }

    @Override
    public String getValue() {
        return Integer.toString(ThreadLocalRandom.current().nextInt(0, 100));
    }

    @Override
    public TSChannel getChannel() {
        return this.chan;
    }
}
