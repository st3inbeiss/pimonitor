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

/**
 * Loads config from an XML-File.
 *
 * @author st3inbeiss
 */
public class ConfigFileLoader {

    private Document doc;

    /**
     * Loads the file.
     *
     * @param configFile The name of the file!
     */
    public ConfigFileLoader(String configFile) {
        try {
            File fXmlFile = new File(configFile);
            System.out.println("Getting config from File: " + fXmlFile.getAbsolutePath());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println("Failed to get config!: " + e.getMessage());
        }
    }

    /**
     * Gets the value from a specific XML-tag. Tag goes like this:
     * <configElement>return value of this function</configElement>
     *
     * @param configElement The name of the tag (see above).
     * @return The value of the tag with the corresponding name.
     */
    public String getConfigValue(String configElement) {
        NodeList nList = doc.getElementsByTagName(configElement);
        String configValue = nList.item(0).getTextContent();
        return configValue;
    }

    /**
     * Gets all the nodes with the name "channel" and parses them.
     *
     * @return HashMap of configured channels.
     */
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
            }
        }
        return channelMap;
    }
}
