package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ccinfom.group5.esports_app.helper.DBCHelper; // Refer here for username, password, etc.

public class Database {
    
    private Connection con;

    public Database() {}

    public boolean initialStatus() {
        this.con = null;
        if (JavaSQLConnection.initializeConnection(this.con) == false) return false;
        
        return true;
    }

    public void createDatabase() {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DBCHelper.dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDatabase() {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DBCHelper.dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + DBCHelper.serverAndPort,
                                                                DBCHelper.username, DBCHelper.password);
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
}