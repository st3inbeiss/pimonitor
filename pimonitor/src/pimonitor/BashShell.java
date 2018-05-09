package pimonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class with only one static method to execute a command on the bash shell.
 *
 * @author st3inbeiss
 */
public class BashShell {

    /**
     * Execute command in bash shell. Use with caution, only for commands which
     * are terminating themselves. Else this gets stuck.
     *
     * @param command The command to be executed.
     * @return The output of the command (basically what's printed in the
     * shell).
     */
    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

        } catch (IOException | InterruptedException e) {
            if (Config.isDebugMode()) {
                System.out.println("Failed to execute command: " + e.toString());
            }
        }
        return output.toString();
    }
}
