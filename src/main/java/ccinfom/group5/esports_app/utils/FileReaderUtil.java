package ccinfom.group5.esports_app.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ccinfom.group5.esports_app.Driver;
import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.model.tables.*;


public class FileReaderUtil {
    
    public static void loadPlayers(String filepath, Database database) {
        int count = 0;

        try (Scanner scanner = new Scanner(new File(filepath))) {   
            boolean isFirstLine = true;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
        
                if (isFirstLine) {
                    for (String columnName : columns) { // Store column names
                        database.getPlayerColumnNames().add(columnName.trim()); 
                    }
                    isFirstLine = false;
                    continue; // Skip below code for first line of csv
                }

                String playerID;
                String lastName;
                String firstName;
                int age;
                String country;
                String teamName;

                playerID = columns[0].trim();
                lastName = columns[1].trim();
                firstName = columns[2].trim();
                age = Integer.parseInt(columns[3].trim());
                country = columns[4].trim();
                teamName = columns[5].trim();
                
                Player player = new Player(playerID, lastName, firstName, age, country, teamName);

                database.getAllPlayers().add(player);

                ++count;
            }
            GeneralUtil.debugPrint(count + " players created successfully"); // DEBUGGING
        } 
        catch (FileNotFoundException e) {
            GeneralUtil.debugPrint("File not found: " + filepath);
            e.printStackTrace();
        }
    }

    public static void printPlayerTable(Database database) {
        if (Driver.terminalLogsEnabled) {
            for (Player player : database.getAllPlayers()) {
                System.out.println(player.getPlayerID() + " " + player.getLastName() + " " + player.getFirstName() 
                                + " " + player.getAge() + " " + player.getCountry() + " " + player.getTeamName());
            }
        }
    }

}
