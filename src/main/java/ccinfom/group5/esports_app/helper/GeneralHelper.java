package ccinfom.group5.esports_app.helper;

import ccinfom.group5.esports_app.Driver;

public class GeneralHelper {

    public static void debugPrint(String message) {
        if (Driver.terminalLogsEnabled) {
            System.out.println(message);
        }
    }

    
}
