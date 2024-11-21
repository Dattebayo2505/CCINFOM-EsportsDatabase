package ccinfom.group5.esports_app.utils;

import java.util.ArrayList;

import ccinfom.group5.esports_app.Driver;

public class GeneralUtil {

    public static String mainDirectory = "src/main/java/ccinfom/group5/esports_app/";
    public static String resourcesDirectory = "src/main/resources/";

    public static ArrayList <String> getTableNames() {
        ArrayList <String> tableNames = new ArrayList <String>();
        tableNames.add("companies");
        tableNames.add("teams");
        tableNames.add("players");
        tableNames.add("playerhistory");
        tableNames.add("sponsorhistory");
        tableNames.add("teamhistory");
        tableNames.add("teamperformancehistory");
        tableNames.add("teamsponsor");
        tableNames.add("teamstats");
        return tableNames;
    }

    public static String[] getArrayTableNames() {
        ArrayList <String> tableNames = getTableNames();
        String[] arrayTableNames = new String[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            arrayTableNames[i] = tableNames.get(i);
        }
        return arrayTableNames;
    }

    public static void debugPrint(String message) {
        if (Driver.terminalLogsEnabled) {
            System.out.println(message);
        }
    }

    
}
