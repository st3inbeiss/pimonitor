package pimonitor;

public class ReaderCPU implements ChannelInterface {
    private final String COMMAND = "w";
    private final TSChannel chan;
    
    public ReaderCPU(){
       chan = Config.getChannelMap().get("cpu");
    }
    
    @Override
    public String getValue(){
        String cpuLoad = BashShell.executeCommand(this.COMMAND);
        String leftPart = cpuLoad.substring(cpuLoad.indexOf("average: ")+9, cpuLoad.length());
        String result = leftPart.substring(0, leftPart.indexOf(".")+3);
        return result;
    }   

    @Override
    public TSChannel getChannel() {
        return this.chan;
    }
}
