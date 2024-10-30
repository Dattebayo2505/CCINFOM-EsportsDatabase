package ccinfom.group5.esports_app.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.model.tables.*;


public class FileReaderUtil {
    
    public static void loadPlayers(String filepath, Database database) {
        int count = 0;

        try (Scanner scanner = new Scanner(new File(filepath))) {   
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");

                int player_id;
                String last_name;
                String first_name;
                int age;
                String nationality;
                String team_name;

                player_id = Integer.parseInt(columns[0]);
                last_name = columns[1];
                first_name = columns[2];
                age = Integer.parseInt(columns[3]);
                nationality = columns[4];
                team_name = columns[5];
                
                Player player = new Player(player_id, last_name, first_name, age, nationality, team_name);

                database.getAllPlayers().add(player);

                ++count;
            }
            GeneralUtil.debugPrint(count + "players created successfully"); // DEBUGGING
        } 
        catch (FileNotFoundException e) {
            GeneralUtil.debugPrint("File not found: " + filepath);
            e.printStackTrace();
        }
    }

}
