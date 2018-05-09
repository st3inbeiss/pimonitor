package pimonitor;

/**
 * Channel to get the CPU-Load.
 *
 * @author st3inbeiss
 */
public class ReaderCPU implements ChannelInterface {

    private final String COMMAND = "w";
    private final TSChannel chan;

    public ReaderCPU() {
        String className = this.getClass().getSimpleName();
        chan = Config.getChannelMap().get(className);
    }

    @Override
    public String getValue() {
        //String cpuLoad = BashShell.executeCommand(this.COMMAND);
        String cpuLoad = "average: 0.55";
        String leftPart = cpuLoad.substring(cpuLoad.indexOf("average: ") + 9, cpuLoad.length());
        String result = leftPart.substring(0, leftPart.indexOf(".") + 3);
        return result;
    }

    @Override
    public TSChannel getChannel() {
        return this.chan;
    }
}
