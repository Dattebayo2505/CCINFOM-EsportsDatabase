package ccinfom.group5.esports_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ccinfom.group5.esports_app.helper.*;

public class Driver {
    public static void main(String[] args) {
        System.out.println("GROUP 5 - Esports Database Application");
        
        JavaSQLConnection.initializeConnection();

        
        
    }
}

class JavaSQLConnection {
    public static void initializeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + DBCHelper.serverAndPort 
                                            + "/" + DBCHelper.db, DBCHelper.username, DBCHelper.password);
            System.out.println("Connection successful");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not Found");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
}