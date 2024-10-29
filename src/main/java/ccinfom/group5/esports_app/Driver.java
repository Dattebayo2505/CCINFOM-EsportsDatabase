package ccinfom.group5.esports_app;

import ccinfom.group5.esports_app.model.*; // Refer here for Database.java
import ccinfom.group5.esports_app.view.*;
import ccinfom.group5.esports_app.controller.*;

public class Driver {

    public static final boolean terminalLogsEnabled = true; // true = ON, false = OFF (for debugging purposes)
    public static void main(String[] args) {
        System.out.println("GROUP 5 - Esports Database Application");
        
        Database database = new Database();
        if (database.initialStatus() == false) return; // END PROGRAM IF CONNECTION FAILS
        database.createDatabase();
        
        GUI gui = new GUI();
        
        new MainController(database, gui);         





        database.removeDatabase(); // Optional??
    }
}

