package pimonitor;

/**
 * This is a skeleton for new readers.
 *
 * @author st3inbeiss
 */
public class ReaderSkeleton implements ChannelInterface {

    private final TSChannel chan;

    /**
     * Constructor WITHOUT PARAMETERS.
     */
    public ReaderSkeleton() {
        String className = this.getClass().getSimpleName();
        chan = Config.getChannelMap().get(className);
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TSChannel getChannel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
