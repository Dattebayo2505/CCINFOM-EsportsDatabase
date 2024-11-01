package ccinfom.group5.esports_app;

import ccinfom.group5.esports_app.model.*; // Refer here for Database.java
import ccinfom.group5.esports_app.view.*;
import ccinfom.group5.esports_app.controller.*;
import ccinfom.group5.esports_app.utils.*;

/*
 * Before starting the program, go to must-edit.txt and edit the following as necessary:
 * - username
 * - password
 * - server
 * - port
 */
public class Driver {

    public static final boolean terminalLogsEnabled = true; // true = ON, false = OFF (for debugging purposes)

    public static void main(String[] args) {
        System.out.println("GROUP 5 - Esports Database Application\n");
        
        FileReaderUtil.readConfigAndSetConnection("src/main/java/ccinfom/group5/esports_app/config-must-edit.txt");

        Database database = new Database();
        if (database.initialStatus() == false) return; // END PROGRAM IF CONNECTION FAILS
        database.createDatabase(); // CREATE DATABASE IF NOT EXISTS esports;
        
        GUI gui = new GUI();
        
        new MainController(database, gui);         

        FileReaderUtil.loadPlayers("src/main/java/ccinfom/group5/esports_app/csv/Players.csv", database);
        FileReaderUtil.printPlayerTable(database);
        // TODO: Uncomment below lines after implementing the respective methods in FileReaderUtil.java
        // FileReaderUtil.loadPlayerEquipment("src/main/java/ccinfom/group5/esports_app/csv/PlayerEquipment.csv");
        // FileReaderUtil.loadTeams("src/main/java/ccinfom/group5/esports_app/csv/Teams.csv");
        // FileReaderUtil.loadMaps("src/main/java/ccinfom/group5/esports_app/csv/Maps.csv");

        database.useDatabase(); // USE esports;

        database.createPlayerTable("players"); // CREATE TABLE players (...)

        database.insertInto("players", database.getAllPlayers());
        // TODO: Uncomment below lines after implementing the respective methods in Database.java
        // database.insertInto("player_equipment", database.getAllPlayerEquipment());
        // database.insertInto("teams", database.getAllTeams());
        // database.insertInto("maps", database.getAllMaps());




        // database.removeDatabase(); // Optional??
    }
}

