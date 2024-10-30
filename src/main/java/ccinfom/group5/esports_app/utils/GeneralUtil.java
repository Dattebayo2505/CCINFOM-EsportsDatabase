package ccinfom.group5.esports_app.utils;

import ccinfom.group5.esports_app.Driver;

public class GeneralUtil {

    public static void debugPrint(String message) {
        if (Driver.terminalLogsEnabled) {
            System.out.println(message);
        }
    }

    
}
