package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ccinfom.group5.esports_app.model.tables.*;
import ccinfom.group5.esports_app.utils.*;

public class Database {
    
    private Connection con;
    private Statement statement;

    private ArrayList<Player> allPlayers;
    private ArrayList<Team> allTeams;
    private ArrayList<Company> allCompanies;
    private ArrayList<TeamSponsor> allTeamSponsors;
    private ArrayList<TeamStats> allTeamStats;
    private ArrayList<PlayerHistory> allPlayerHistories;
    private ArrayList<TeamHistory> allTeamHistories;
    private ArrayList<SponsorHistory> allSponsorHistories;
    private ArrayList<TeamPerformanceHistory> allTeamPerformanceHistories;

    private HashMap<String, String[]> tableColumnNameMap; 
    private HashMap<String, Object[][]> tableDataMap;    

    public Database() {
        this.con = null;
        this.statement = null;
        this.allPlayers = new ArrayList<Player>();
        this.allTeams = new ArrayList<Team>();
        this.allCompanies = new ArrayList<Company>();
        this.allTeamSponsors = new ArrayList<TeamSponsor>();
        this.allTeamStats = new ArrayList<TeamStats>();
        this.allPlayerHistories = new ArrayList<PlayerHistory>();
        this.allTeamHistories = new ArrayList<TeamHistory>();
        this.allSponsorHistories = new ArrayList<SponsorHistory>();
        this.allTeamPerformanceHistories = new ArrayList<TeamPerformanceHistory>();
        this.tableColumnNameMap = new HashMap<String, String[]>();
        this.tableDataMap = new HashMap<String, Object[][]>();
    }

    public void initializeDatabase(List<String> filepaths) {
        FileReaderUtil.getDatabase(filepaths, con);
    }

    public void initiateModel(List<String> tables) {
        int i, columnCount, j, rowCount;
        String query, columnName, columnType;
        String[] columnNames = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData;
        ArrayList<ArrayList<Object>> tableRecordsList;
        Object[][] tableRecords;
        Object[] tableRecord;
        

        for (String table : tables) {
            try {
                statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_READ_ONLY);
                query = "SELECT * FROM " + table;
                resultSet = statement.executeQuery(query);
                
                metaData = resultSet.getMetaData();
                columnCount = metaData.getColumnCount();
                
                resultSet.last(); // Move to the last row
                rowCount = resultSet.getRow(); // Get the row number (which is the row count)
                resultSet.beforeFirst(); // Move back to the beginning

                j = 0;
                tableRecords = new Object[rowCount][columnCount];
                columnNames = new String[columnCount];
                while (resultSet.next()) {
                    tableRecord = new Object[columnCount];
                    for (i=1; i<=columnCount; i++) {
                        columnName = metaData.getColumnName(i);
                        columnType = metaData.getColumnTypeName(i);

                        columnNames[i-1] = columnName;

                        switch (columnType) {
                            case "INT":
                                int num = resultSet.getInt(columnName);
                                tableRecord[i-1] = num;
                                break;
                            case "VARCHAR":
                                String str = resultSet.getString(columnName);
                                tableRecord[i-1] = str;
                                break;
                            case "DATE":
                                String date = resultSet.getString(columnName);
                                tableRecord[i-1] = date;
                                break;
                            case "DECIMAL":
                                double dec = resultSet.getDouble(columnName);
                                tableRecord[i-1] = dec;
                                break;
                            case "NULL":
                                tableRecord[i-1] = null;
                                break;
                            default:
                                System.out.println("Unknown type");
                                break;
                        }

                    }
                    tableRecords[j] = tableRecord;

                    switch (table) {
                        case "companies":
                            Company company = new Company(
                                (int) tableRecord[0], 
                                tableRecord[1].toString()
                            );
                           
                            allCompanies.add(company);
                            break;
                        case "teams":
                            Team team = new Team(
                                tableRecord[0].toString(), // team
                                tableRecord[1].toString(), // captain
                                tableRecord[2].toString(), // region
                                tableRecord[3].toString(), // country
                                tableRecord[4].toString()  // status
                            );

                            allTeams.add(team);
                            break;
                        case "players":
                    }
                    
                    // TODO: Add Object[][] here

                    ++j;
                }
                tableDataMap.put(table, tableRecords);
                tableColumnNameMap.put(table, columnNames);
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
            
    }
    
    // TODO: ADD OTHER QUERY/UPDATE METHODS HERE - JOB 

    public Object[][] getTableData(String table) {
        Object data[][];


        return null;
    }

    public boolean initialStatus() {
        this.con = JavaSQLConnection.tryMakeConnection();
        return this.con != null;        
    }

    public Connection getCon() {
        return con;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }
    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }
    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }
    public void setAllTeams(ArrayList<Team> allTeams) {
        this.allTeams = allTeams;
    }
    public ArrayList<Company> getAllCompanies() {
        return allCompanies;
    }
    public void setAllCompanies(ArrayList<Company> allCompanies) {
        this.allCompanies = allCompanies;
    }
    public ArrayList<TeamSponsor> getAllTeamSponsors() {
        return allTeamSponsors;
    }
    public void setAllTeamSponsors(ArrayList<TeamSponsor> allTeamSponsors) {
        this.allTeamSponsors = allTeamSponsors;
    }
    public ArrayList<TeamStats> getAllTeamStats() {
        return allTeamStats;
    }
    public void setAllTeamStats(ArrayList<TeamStats> allTeamStats) {
        this.allTeamStats = allTeamStats;
    }
    public ArrayList<PlayerHistory> getAllPlayerHistories() {
        return allPlayerHistories;
    }
    public void setAllPlayerHistories(ArrayList<PlayerHistory> allPlayerHistories) {
        this.allPlayerHistories = allPlayerHistories;
    }
    public ArrayList<TeamHistory> getAllTeamHistories() {
        return allTeamHistories;
    }
    public void setAllTeamHistories(ArrayList<TeamHistory> allTeamHistories) {
        this.allTeamHistories = allTeamHistories;
    }
    public ArrayList<SponsorHistory> getAllSponsorHistories() {
        return allSponsorHistories;
    }
    public void setAllSponsorHistories(ArrayList<SponsorHistory> allSponsorHistories) {
        this.allSponsorHistories = allSponsorHistories;
    }
    public ArrayList<TeamPerformanceHistory> getAllTeamPerformanceHistories() {
        return allTeamPerformanceHistories;
    }
    public void setAllTeamPerformanceHistories(ArrayList<TeamPerformanceHistory> allTeamPerformanceHistories) {
        this.allTeamPerformanceHistories = allTeamPerformanceHistories;
    }

    public HashMap<String, String[]> getTableColumnNameMap() {
        return tableColumnNameMap;
    }

    public HashMap<String, Object[][]> getTableDataMap() {
        return tableDataMap;
    }

}

