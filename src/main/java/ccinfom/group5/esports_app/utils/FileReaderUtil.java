package ccinfom.group5.esports_app.utils;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// import ccinfom.group5.esports_app.Driver;
import ccinfom.group5.esports_app.model.*;

public class FileReaderUtil {
    
    public static void getDatabase(List<String> filepaths, Connection con) {
        String line, mainQuery = null;
        StringBuilder query = new StringBuilder();

        for (String filepath : filepaths) {
            try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
                Statement statement = con.createStatement();
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty() || line.startsWith("-")) {
                        continue; // Skip empty lines and comments
                    }
                    query.append(line);
                    if (line.endsWith(";")) {
                        mainQuery = query.toString();
                        statement.executeUpdate(mainQuery);
                        query.setLength(0); // Clear the query
                    }
                }
            }
            catch (SQLException e) {
                GeneralUtil.debugPrint("Error executing SQL query: " + mainQuery);
                e.printStackTrace();
            } catch (IOException e) {
                GeneralUtil.debugPrint("Error reading database file: " + filepath);
                e.printStackTrace();
            }
        }

    }
    

/* 
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
*/
    public static void readConfigAndSetConnection(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line, key, value;

            key = null;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty())
                    continue;
                    
                if (line.charAt(0) == '#') 
                    key = line.substring(2);   

                
                value = br.readLine().trim();
    
                switch (key) {
                    case "password":
                        JavaSQLConnection.setPassword(value);
                        break;
                    case "username":
                        JavaSQLConnection.setUsername(value);
                        break;
                    case "dbName":
                        JavaSQLConnection.setDbName(value);
                        break;
                    case "server":
                        JavaSQLConnection.setServer(value);
                        break;
                    case "port":
                        JavaSQLConnection.setPort(value);
                        break;
                    default:
                        GeneralUtil.debugPrint("No such key: " + key);
                        break;
                }
            }
            GeneralUtil.debugPrint("Configuration loaded successfully\n");
        } catch (IOException e) {
            GeneralUtil.debugPrint("Error reading configuration file: " + filepath + "\n");
            e.printStackTrace();
        }
    }
}
