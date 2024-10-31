package ccinfom.group5.esports_app;

import ccinfom.group5.esports_app.model.*; // Refer here for Database.java
import ccinfom.group5.esports_app.view.*;
import ccinfom.group5.esports_app.controller.*;
import ccinfom.group5.esports_app.utils.*;

public class Driver {

    public static final boolean terminalLogsEnabled = true; // true = ON, false = OFF (for debugging purposes)
    public static void main(String[] args) {
        System.out.println("GROUP 5 - Esports Database Application");
        
        Database database = new Database();
        if (database.initialStatus() == false) return; // END PROGRAM IF CONNECTION FAILS
        database.createDatabase();
        
        GUI gui = new GUI();
        
        new MainController(database, gui);         

        FileReaderUtil.loadPlayers("src/main/java/ccinfom/group5/esports_app/csv/Players.csv", database);
        // FileReaderUtil.loadPlayerEquipment("src/main/java/ccinfom/group5/esports_app/csv/PlayerEquipment.csv");
        // FileReaderUtil.loadTeams("src/main/java/ccinfom/group5/esports_app/csv/Teams.csv");
        // FileReaderUtil.loadMaps("src/main/java/ccinfom/group5/esports_app/csv/Maps.csv");





        database.removeDatabase(); // Optional??
    }
}

