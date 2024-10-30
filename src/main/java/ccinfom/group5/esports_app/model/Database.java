package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import ccinfom.group5.esports_app.model.tables.*;

import ccinfom.group5.esports_app.helper.DBCHelper; // Refer here for username, password, etc.

public class Database {
    
    private Connection con;
    private Statement statement;

    private ArrayList<Player> allPlayers;
    private ArrayList<PlayerEquipment> allPlayerEquipment;
    private ArrayList<Team> allTeams;
    private ArrayList<Map> allMaps;
    
    private ArrayList<String> playerColumnNames;
    private ArrayList<String> playerEquipmentColumnNames;
    private ArrayList<String> teamColumnNames;
    private ArrayList<String> mapColumnNames;

    public Database() {
        this.con = null;
        this.statement = null;
        this.allPlayers = new ArrayList<Player>();
        this.allPlayerEquipment = new ArrayList<PlayerEquipment>();
        this.allTeams = new ArrayList<Team>();
        this.allMaps = new ArrayList<Map>();
    }

    public void initializeTables() {
        
    }


    /**
     * Adds a column to the specified table with the given column name and type.
     *
     * @param tableName the name of the table to which the column will be added
     * @param columnName the name of the column to be added
     * @param columnType the type of the column to be added (e.g., INT, FLOAT)
     */
    public void addColumnsToTable(String tableName, String columnName, String columnType) {
        this.statement = null;
        try {
            statement = con.createStatement();
            String sql = "ALTER TABLE " + tableName + " ADD " + columnName + " " + columnType;
            statement.executeUpdate(sql);
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

    /**
     * Adds a 'String' type column to the specified table with the given column name and length.
     *
     * @param tableName the name of the table to which the column will be added
     * @param columnName the name of the column to be added
     * @param length the length of the VARCHAR column to be added
     */
    public void addColumnsToTable(String tableName, String columnName, int length) {
        this.statement = null;
        try {
            statement = con.createStatement();
            String sql = "ALTER TABLE " + tableName + " ADD " + columnName + " VARCHAR(" + length + ")";
            statement.executeUpdate(sql);
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

    // TODO: ADD OTHER QUERY/UPDATE METHODS HERE - JOB 




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

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public ArrayList<PlayerEquipment> getAllPlayerEquipment() {
        return allPlayerEquipment;
    }

    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }

    public ArrayList<Map> getAllMaps() {
        return allMaps;
    }

    public ArrayList<String> getPlayerColumnNames() {
        return playerColumnNames;
    }

    public ArrayList<String> getPlayerEquipmentColumnNames() {
        return playerEquipmentColumnNames;
    }

    public ArrayList<String> getTeamColumnNames() {
        return teamColumnNames;
    }

    public ArrayList<String> getMapColumnNames() {
        return mapColumnNames;
    }


}

