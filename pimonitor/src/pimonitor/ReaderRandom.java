package pimonitor;

import java.util.concurrent.ThreadLocalRandom;

public class ReaderRandom implements ChannelInterface {

    //private final ThingspeakSender tSender;
    private final TSChannel chan;

    public ReaderRandom() {
         chan = Config.getChannelMap().get("random");
        //tSender = new ThingspeakSender(chan);
    }

    @Override
    public String getValue() {
        return Integer.toString(ThreadLocalRandom.current().nextInt(0, 100));
    }
    
    @Override
    public TSChannel getChannel(){
        return this.chan;
    }
    /*  
    public void update(){
        try {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            tSender.sendData(Integer.toString(randomNum));
        } catch (Exception e) {
            System.out.println("Failed to send Data.");
            System.out.println(e.toString());
        }
    }*/
}
