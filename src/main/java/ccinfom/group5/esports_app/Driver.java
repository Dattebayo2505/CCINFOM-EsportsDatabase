package ccinfom.group5.esports_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ccinfom.group5.esports_app.helper.*; // Refer here for username, passwords, etc.

public class Driver {
    public static void main(String[] args) {
        Connection con = null;

        System.out.println("GROUP 5 - Esports Database Application");
        
        if (JavaSQLConnection.initializeConnection(con) == false) return; // END PROGRAM IF NO CONNECTION

        // try {
        //     Statement statement = con.createStatement();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

        // JavaSQLConnection.removeDatabase(con);
        
    }
}

class JavaSQLConnection {

    public static boolean initializeConnection(Connection con) {
        if (tryMakeConnection() != null) {
            con = tryMakeConnection();
            return true;
        }
        System.out.println("Connection failed");
        return false;
    }

    public static Connection tryMakeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + DBCHelper.serverAndPort 
                                            + "/" + DBCHelper.db, DBCHelper.username, DBCHelper.password);
            System.out.println("Connection established");

            return connection;
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not Found");
            e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addDatabase(Connection con) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DBCHelper.db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeDatabase(Connection con) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DBCHelper.db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}