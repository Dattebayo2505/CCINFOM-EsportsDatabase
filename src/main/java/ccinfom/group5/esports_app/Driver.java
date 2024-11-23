package ccinfom.group5.esports_app;

import ccinfom.group5.esports_app.model.*; // Refer here for Database.java
import ccinfom.group5.esports_app.view_deprecate.OldGUI;
import ccinfom.group5.esports_app.view.GUI;

import java.util.ArrayList;

import ccinfom.group5.esports_app.controller.*;
import ccinfom.group5.esports_app.utils.*;

/*
 * Before starting the program, go to config-must-edit.txt and edit the following as necessary 
 * (Refering to your MySQL account):
 * - username
 * - password
 * - server
 * - port
 */
public class Driver {
    public static final boolean terminalLogsEnabled = true; // true = ON, false = OFF (for debugging purposes)

    public static void main(String[] args) {
        System.out.println("GROUP 5 - Esports Database Application\n");
        
        FileReaderUtil.readConfigAndSetConnection(GeneralUtil.mainDirectory + "config-must-edit.txt");

        Database database = new Database();
        if (database.initialStatus() == false) return;

        ArrayList<String> sqlDirectory = new ArrayList<String>();
        sqlDirectory.add(GeneralUtil.resourcesDirectory + "sql/esports.sql"); // Main database

        for (String tableName : GeneralUtil.getTableNames()) {
            sqlDirectory.add(GeneralUtil.resourcesDirectory + "sql/" + tableName + ".sql");
        }

        database.initializeDatabase(sqlDirectory);
        database.initiateModel(GeneralUtil.getTableNames());
        
        
        
        // OldGUI oldGui = new OldGUI();
        GUI gui = new GUI();
        gui.setVisible(true);
        
        new MainController(database, gui, database.getCon());            
        
    }
}

