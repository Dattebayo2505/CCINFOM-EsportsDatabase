package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ccinfom.group5.esports_app.helper.DBCHelper; // Refer here for username, password, etc.

public class Database {
    
    private Connection con;
    private Statement statement;

    public Database() {}

    public boolean initialStatus() {
        this.con = JavaSQLConnection.tryMakeConnection();
        return this.con != null;        
    }

    public void createDatabase() {
        this.statement = null;
        try {
            statement = this.con.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DBCHelper.dbName);
            
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeDatabase() {
        this.statement = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DBCHelper.dbName);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

