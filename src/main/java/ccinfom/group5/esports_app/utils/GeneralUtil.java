package ccinfom.group5.esports_app.utils;

import ccinfom.group5.esports_app.Driver;

public class GeneralUtil {

    public static String mainDirectory = "src/main/java/ccinfom/group5/esports_app/";
    public static String resourcesDirectory = "src/main/resources/";

    public static void debugPrint(String message) {
        if (Driver.terminalLogsEnabled) {
            System.out.println(message);
        }
    }

    
}
