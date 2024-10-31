package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import ccinfom.group5.esports_app.model.tables.*;
import ccinfom.group5.esports_app.utils.GeneralUtil;
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

        this.playerColumnNames = new ArrayList<String>();
        this.playerEquipmentColumnNames = new ArrayList<String>();
        this.teamColumnNames = new ArrayList<String>();
        this.mapColumnNames = new ArrayList<String>();
    }

    public void createPlayerTable(String tableName) {
        StringBuilder stringQuery;
        String columnName;
        
        this.statement = null;
        try {
            statement = con.createStatement();
            stringQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS " 
                                                          + tableName + " (");

            for (int i = 0; i < playerColumnNames.size(); i++) {
                columnName = playerColumnNames.get(i);
                if (columnName.equals("age")) {
                    stringQuery.append(columnName).append(" INT, ");
                } 
                else {
                    stringQuery.append(columnName).append(" VARCHAR(50), ");
                }
            }

            stringQuery.setLength(stringQuery.length() - 2); // Remove the last comma and space

            // Add primary key and foreign key constraints
            stringQuery.append(", PRIMARY KEY (player_id)"); // TODO: REMOVE ONCE ALL TABLES ARE IMPLEMENTED
            // stringQuery.append(", PRIMARY KEY (player_id), FOREIGN KEY (team_name) REFERENCES teams(team_name))"); // TODO: Uncomment once teams table is implemented
            stringQuery.append(");");

            statement.executeUpdate(stringQuery.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public void useDatabase() {
        this.statement = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("USE " + DBCHelper.dbName);
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

    public void insertInto(String tableName, ArrayList<?> record) {
        int i, j;
        StringBuilder stringQuery, finalStringQuery;
        Object value;
    
        this.statement = null;

        try {
            statement = con.createStatement();

            stringQuery = new StringBuilder("INSERT INTO " + tableName + "(");
            
            for (j = 0; j < getPlayerColumnNames().size(); j++) {
                if (j == getPlayerColumnNames().size() - 1) {
                    stringQuery.append(getPlayerColumnNames().get(j));
                } else {
                    stringQuery.append(getPlayerColumnNames().get(j)).append(", ");
                }
            }

            stringQuery.append(") VALUES (");

            for (i = 0; i < record.size(); i++) {
                value = record.get(i);
            
                finalStringQuery = new StringBuilder(stringQuery);
            
                if (value instanceof Player) {
                    finalStringQuery.append(((Player) value).getAllDetails());
                }
                // TODO: Add from other tables/classes here
                // else if (value instanceof PlayerEquipment) {
                //     stringQuery.append(((PlayerEquipment) value).getAllDetails());
                // }
                // else if (value instanceof Team) {
                //     stringQuery.append(((Team) value).getAllDetails());
                // }
                // else if (value instanceof Map) {
                //     stringQuery.append(((Map) value).getAllDetails());
                // }
                
                finalStringQuery.append(");");
                
                statement.executeUpdate(finalStringQuery.toString()); // Execute each statement individually to avoid large string
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

