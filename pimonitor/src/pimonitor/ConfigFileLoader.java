package pimonitor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigFileLoader {

    private Document doc;

    public ConfigFileLoader(String configFile) {
        try {
            File fXmlFile = new File(configFile);
            System.out.println("Getting config from File: " + fXmlFile.getAbsolutePath());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println("Failed to get config!");
            e.printStackTrace();
        }
    }

    public String getConfigValue(String configElement) {
        NodeList nList = doc.getElementsByTagName(configElement);
        String configValue = nList.item(0).getTextContent();
        return configValue;
    }

    public HashMap<String, TSChannel> getChannels() {
        HashMap channelMap = new HashMap();
        NodeList channelNodes = doc.getElementsByTagName("channel");
        for (int i = 0; i < channelNodes.getLength(); i++) {
            Node nNode = channelNodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                TSChannel tsChannel = new TSChannel(
                        eElement.getElementsByTagName("key").item(0).getTextContent(),
                        eElement.getElementsByTagName("field").item(0).getTextContent());
                
                channelMap.put(eElement.getAttribute("id"), tsChannel);

                if (Config.getDebugMode()) {
                    String debugMsg = "Loaded Channel with ID "
                            + eElement.getAttribute("id")
                            + " with channel Key "
                            + eElement.getElementsByTagName("key").item(0).getTextContent()
                            + " and Field #"
                            + eElement.getElementsByTagName("field").item(0).getTextContent();

                    System.out.println(debugMsg);
                }
            }

        }
        return channelMap;
    }
}
