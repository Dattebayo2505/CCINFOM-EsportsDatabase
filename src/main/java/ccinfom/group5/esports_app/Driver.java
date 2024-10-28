package ccinfom.group5.esports_app;

import ccinfom.group5.esports_app.model.*; // Refer here for Database.java
import ccinfom.group5.esports_app.view.*;
import ccinfom.group5.esports_app.controller.*;


public class Driver {
    public static void main(String[] args) {
        System.out.println("GROUP 5 - Esports Database Application");

        new MainController();
        new GUI();

        Database database = new Database();
        if (database.initialStatus() == false) return;

        database.createDatabase(); // END PROGRAM IF NO CONNECTION
         

        // database.removeDatabase(); 
    }
}

