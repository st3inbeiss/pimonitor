package pimonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Makes a GET-Request to a specified URL.
 *
 * @author st3inbeiss
 */
public class HTTPGetter {

    private final String USER_AGENT = "Mozilla/5.0";
    private int responseCode;
    private StringBuilder response;

    public HTTPGetter() {

    }

    /**
     * Sends the GET-Request to the specified URL.
     *
     * @param urlString The URL.
     */
    public void sendData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            this.responseCode = con.getResponseCode();

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            if (Config.isDebugMode()) {
                System.out.println("Called URL: " + urlString);
                System.out.println("Server Response: " + responseCode + ": " + response);
            }

        } catch (IOException e) {
            System.out.println("Failed to send to Thingspeak. Server Response: "
                    + responseCode + ": " + response);
        }
    }
}
