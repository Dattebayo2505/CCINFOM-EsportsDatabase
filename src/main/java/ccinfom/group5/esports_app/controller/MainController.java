package ccinfom.group5.esports_app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.utils.FileReaderUtil;
import ccinfom.group5.esports_app.view.GUI;

public class MainController implements ActionListener {
    
    private Database database;
    private GUI gui;
    private Connection con;
    private Statement statement;


    public MainController(Database database, GUI gui, Connection con) {
        this.database = database;
        this.gui = gui;
        this.con = con;     

        gui.addClickListener(this);

        
        gui.setPlayersComboBoxModel(getIDs("players", "player_id", "active"));
        gui.setTeamsComboBoxModel(getIDs("teams", "team", "active"));
        // TODO add gui updatesponsorcomboboxmodel
        gui.setSponsorsComboBoxModel(getIDs("companies", "company"));


        gui.getMainViewTable().setModel(initializeTable("players"));
    }

    private DefaultTableModel initializeTable(String tableName) {
        String[] columnNames = null;
        Object[][] data = null;
        ResultSet rs;
        ResultSetMetaData metaData;
        int columnCount, rowCount, i;
        String query = "SELECT * FROM " + tableName;

        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

            rs = statement.executeQuery(query);

            metaData = rs.getMetaData();
            columnCount = metaData.getColumnCount();

            rs.last();
            rowCount = rs.getRow();
            rs.beforeFirst();

            columnNames = new String[columnCount];
            data = new Object[rowCount][columnCount];

            columnNames = FileReaderUtil.setColumnNames(columnCount, metaData);

            i=0;
            while (rs.next()) {
                data[i] = FileReaderUtil.setTableRecord(columnCount, rs, metaData);
                ++i;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        return model;
    }

    private void initializeTable(String[] columnNames, Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        gui.getMainViewTable().setModel(model);

    }

    private void doExecuteQuery() {
        int rowCount, columnCount, i;
        Object[][] data;
        String[] columnNames;
        ResultSetMetaData metaData;
        String query = gui.getQueryMainViewTxtArea().getText();
        if (query.trim().isEmpty()) {
            JOptionPane.showMessageDialog(gui.getMainViewPanel(), "Query cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (isSemicolonPresent(query)) {
            JOptionPane.showMessageDialog(gui.getMainViewPanel(), "Multiple queries not allowed", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = statement.executeQuery(query);

            resultSet.last(); 
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            
            metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();

            data = new Object[rowCount][columnCount];
            columnNames = new String[columnCount];

            columnNames = FileReaderUtil.setColumnNames(columnCount, metaData);

            i=0;
            while (resultSet.next()) {
                data[i] = FileReaderUtil.setTableRecord(columnCount, resultSet, metaData);
                ++i;
            }

            initializeTable(columnNames, data);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing query: \n" + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void doTransferPlayer() throws SQLException {
        String playerID = (String) gui.getPlayerTransferPlayerComboBox().getSelectedItem();
        String newTeam = (String) gui.getTeamTransferPlayerTransacComboBox().getSelectedItem();
        
        ResultSet rs = chooseRecord("players", playerID);
        String oldTeam = rs.getString("current_team");

        if (oldTeam.equals(newTeam)) {
            JOptionPane.showMessageDialog(null, "The player is already in the selected team.", 
                            "Transfer Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String date = gui.getYearTransferPlayerTxtField().getText() 
                    + "-" + gui.getMonthTransferPlayerTxtField().getText() 
                    + "-" + gui.getDayTransferPlayerTxtField().getText();

        dbTransacPlayer(playerID, date, oldTeam, newTeam);
        // transaction.playerTransfer(player, date, newTeam, date);

        gui.getMainViewTable().setModel(initializeTable("playerhistory"));

        JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Player transferred successfully.", 
                        "Transfer Success", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent e)  {
        Object source = e.getSource();

        // Main Menu Page
        if (source == gui.getMainViewBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainview");
        }
        else if (source == gui.getMakeTransacBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "maketransac");
        }
        else if (source == gui.getGenReportsBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "genreports");
        }
        else if (source == gui.getExitBtn()) {
            System.exit(0);
        }

        // Main View Page
        if (source == gui.getExecuteQueryMainViewBtn()) {
            doExecuteQuery();
        }
        else if (source == gui.getTablesMainViewComboBox()) {
            String selectedTable = (String) gui.getTablesMainViewComboBox().getSelectedItem();
            gui.getMainViewTable().setModel(initializeTable(selectedTable));
        }
        else if (source == gui.getMakeTransacBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "maketransac");
        }
        else if (source == gui.getMainViewMainMenuBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainmenu");
        }


        // Make Transaction Page
        else if (source == gui.getFinalTransferPlayerBtn()) {
            if (gui.getYearTransferPlayerTxtField().getText().isEmpty() 
             || gui.getMonthTransferPlayerTxtField().getText().isEmpty() 
             || gui.getDayTransferPlayerTxtField().getText().isEmpty()) {
                JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "All dates cannot be empty", "Transfer Error", 
                                JOptionPane.ERROR_MESSAGE);
                return;
            }

            int response = JOptionPane.showConfirmDialog(gui.getMakeTransacPanel(), "Are you sure you want to transfer the player?",
                                        "Confirm Transfer", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                try {
                    doTransferPlayer();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            setMainViewTableView();
        }

        else if (source == gui.getTeamSponsorAddComboBox()) {
            doAddSponsor();
        }

        else if(source == gui.getFinalAddSponsorTeamBtn()) {
            String team = (String) gui.getSponsorAddComboBox().getSelectedItem();
        } 
        
        else if (source == gui.getFinalDissolveTeamBtn()) {
            String team = (String) gui.getTeamTransferTeamDissolveComboBox().getSelectedItem();
            if (gui.getYearDissolveTeamTxtField().getText().isEmpty() 
             || gui.getMonthDissolveTeamTxtField().getText().isEmpty() 
             || gui.getDayDissolveTeamTxtField().getText().isEmpty()) {
                JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "All dates cannot be empty", "Dissolve Error", 
                                JOptionPane.ERROR_MESSAGE);
                return;
            }

            int response = JOptionPane.showConfirmDialog(gui.getMakeTransacPanel(), "Are you sure you want to dissolve the team?",
                                        "Confirm Dissolve", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                try {
                    dbDissolveTeam(team);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                gui.setTeamsComboBoxModel(getIDs("teams", "team"));

                JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Team dissolved successfully.", 
                "Dissolve Success", JOptionPane.INFORMATION_MESSAGE);
                
                
                
                setMainViewTableView();
            }
            
            
        }
        
        // else if (source == gui.getFinalAddSponsor()) {

        // }
        

        else if (source == gui.getTeamsUpdateStatsComboBox() || 
                 source == gui.getTeamsUpdateStatsComboBox2()) {

            updateComboBoxes();
        }
        
        else if (source == gui.getFinalTeamsUpdateStatsBtn()) {
            dpUpdateTeamStats();

            setMainViewTableView();

            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Team stats updated successfully.", 
                            "Update Success", JOptionPane.INFORMATION_MESSAGE);
        }




        else if (source == gui.getMainMenuTransacBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainmenu");
        }

        
        // Generate Reports Page




    }

    private void setMainViewTableView() {
        String selectedTable = (String) gui.getTablesMainViewComboBox().getSelectedItem();
        gui.getMainViewTable().setModel(initializeTable(selectedTable));
    }

    private boolean isSemicolonPresent(String query) {
        int semicolonCount = 0;
        for (char c : query.toCharArray()) {
            if (c == ';') {
                semicolonCount++;
            }
            if (semicolonCount >= 2) {
                return true;
            }
        }
        return false;
    }

    private void doAddSponsor() {
        String team = (String) gui.getTeamSponsorAddComboBox().getSelectedItem();
        int numSponsor = 0;

        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM teamsponsor WHERE team = '" + team + "'");

            if (resultSet.next()) {
                numSponsor = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Error executing query: \n" + 
                            ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        if (numSponsor == 0) 
            gui.getCurrentTeamSponsorLbl().setText("Current Team has no sponsors.");
        else if (numSponsor == 1)
            gui.getCurrentTeamSponsorLbl().setText("Current Team has 1 sponsor.");
        else
            gui.getCurrentTeamSponsorLbl().setText("Current Team has " + numSponsor + " sponsors.");


        dpAddSponsor(team, numSponsor);
    }

    private void dpAddSponsor(String team, int numSponsor) {
        String sponsor = (String) gui.getTeamSponsorAddComboBox().getSelectedItem();
        String dateStart = gui.getYearSponsorAddTxtField().getText() 
                    + "-" + gui.getMonthSponsorAddTxtField().getText() 
                    + "-" + gui.getDaySponsorAddTxtField().getText();

        String dateEnd = gui.getYearSponsorAddTxtField1().getText() 
                    + "-" + gui.getMonthSponsorAddTxtField1().getText() 
                    + "-" + gui.getDaySponsorAddTxtField1().getText();

        int contractAmount = (int) gui.getAddSponsorTeamSpinner().getValue();

        if (numSponsor == 3) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Team has reached MAX sponsors.", 
                            "Sponsor Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String getSponsorIDFromNameQuery = "SELECT company FROM companies WHERE company = ?";



        String insertQuery1 = "INSERT INTO teamsponsor (sponsor_id, team, contract_amount, contract_start, contract_end) " 
                    + "VALUES (?, ?, ?, ?, ?)";
        String insertQuery2 = "INSERT INTO sponsorhistory (history_id, sponsor_id, team, contract_amount, contract_start, contract_end) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(getSponsorIDFromNameQuery);
            PreparedStatement pstmt1 = con.prepareStatement(insertQuery1);
            PreparedStatement pstmt2 = con.prepareStatement(insertQuery2)
            ) {

            pstmt.setString(1, sponsor);
            ResultSet rs = pstmt.executeQuery();
            String companyID = rs.getString("company_id");

            pstmt1.setInt(1, getRowCount("teamsponsor") + 1);
            pstmt1.setString(2, companyID);
            pstmt1.setInt(3, contractAmount);
            pstmt1.setString(4, dateStart);
            pstmt1.setString(5, dateEnd);

            pstmt2.setInt(1, getRowCount("sponsorhistory") + 1);
            pstmt2.setString(2, sponsor);
            pstmt2.setString(3, companyID);
            pstmt2.setInt(4, contractAmount);
            pstmt2.setString(5, dateStart);
            pstmt2.setString(6, dateEnd);

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }        

    private void dpUpdateTeamStats() {
        String team1 = (String) gui.getTeamsUpdateStatsComboBox().getSelectedItem();
        String team2 = (String) gui.getTeamsUpdateStatsComboBox2().getSelectedItem();
        String winner = (String) gui.getTeamsWinnerUpdateStatsComboBox().getSelectedItem();

        int winnings = (int) gui.getTeamsUpdateStatsSpinner().getValue();

        String date = gui.getYearTeamsUpdateStatsTxtField().getText() 
                    + "-" + gui.getMonthTeamsUpdateStatsTxtField().getText() 
                    + "-" + gui.getDayTeamsUpdateStatsTxtField().getText();

        String insertTeamPerformanceQuery = "INSERT INTO teamperformancehistory (history_id, team, match_date, result, winnings) VALUES (?, ?, ?, ?, ?)";
        String updateTeamStatsQuery = "UPDATE teamstats SET total_winnings = total_winnings + ?, wins = wins + ?, losses = losses + ? WHERE team = ?";

        try (
            PreparedStatement pstmt1 = con.prepareStatement(insertTeamPerformanceQuery);
            PreparedStatement pstmt2 = con.prepareStatement(insertTeamPerformanceQuery);
            PreparedStatement pstmt3 = con.prepareStatement(updateTeamStatsQuery);
            PreparedStatement pstmt4 = con.prepareStatement(updateTeamStatsQuery);
            ) {

            // Insert into teamperformance for team1
            pstmt1.setInt(1, getRowCount("teamperformancehistory") + 1);
            pstmt1.setString(2, team1);
            pstmt1.setString(3, date);
            pstmt1.setString(4, team1.equals(winner) ? "win" : "loss");
            pstmt1.setInt(5, team1.equals(winner) ? winnings : 0);

            // Insert into teamperformance for team2
            pstmt2.setInt(1, getRowCount("teamperformancehistory") + 2);
            pstmt2.setString(2, team2);
            pstmt2.setString(3, date);
            pstmt2.setString(4, team2.equals(winner) ? "win" : "loss");
            pstmt2.setInt(5, team2.equals(winner) ? winnings : 0);

            // Update teamstats for team1
            pstmt3.setInt(1, winnings);
            pstmt3.setInt(2, team1.equals(winner) ? 1 : 0);
            pstmt3.setInt(3, team1.equals(winner) ? 0 : 1);
            pstmt3.setString(4, team1);

            // Update teamstats for team2
            pstmt4.setInt(1, winnings);
            pstmt4.setInt(2, team2.equals(winner) ? 1 : 0);
            pstmt4.setInt(3, team2.equals(winner) ? 0 : 1);
            pstmt4.setString(4, team2);

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
            
    }

    private void dbDissolveTeam(String team) {
        String date = gui.getYearDissolveTeamTxtField().getText() 
                + "-" + gui.getMonthDissolveTeamTxtField().getText() 
                + "-" + gui.getDayDissolveTeamTxtField().getText();
    
        String getCreationDateQuery = "SELECT creation_date FROM teamhistory WHERE team = ? ORDER BY history_id ASC LIMIT 1";
        String updatePlayersQuery = "UPDATE players SET current_team = NULL, status = 'inactive' WHERE current_team = ?";
        String updateTeamQuery = "UPDATE teams SET captain = NULL, country = NULL, region = NULL, status = 'inactive' WHERE team = ?";
        String insertTeamHistoryQuery = "INSERT INTO teamhistory (history_id, team, creation_date, disband_date) VALUES (?, ?, ?, ?)";
        String getPlayersQuery = "SELECT player_id FROM players WHERE current_team = ?";
    
        try (
            PreparedStatement pstmt1 = con.prepareStatement(getCreationDateQuery);
            PreparedStatement pstmt2 = con.prepareStatement(updatePlayersQuery);
            PreparedStatement pstmt3 = con.prepareStatement(updateTeamQuery);
            PreparedStatement pstmt4 = con.prepareStatement(insertTeamHistoryQuery);
            PreparedStatement pstmt5 = con.prepareStatement(getPlayersQuery)
        ) {
            // Get the creation date of the team
            pstmt1.setString(1, team);
            ResultSet rs = pstmt1.executeQuery();
            String creationDate = null;
            if (rs.next()) {
                creationDate = rs.getString("creation_date");
            }
    
            // Get the list of players affected by the team dissolution
            pstmt5.setString(1, team);
            ResultSet playersRs = pstmt5.executeQuery();
            while (playersRs.next()) {
                String playerID = playersRs.getString("player_id");
                // Add the player to playerhistory with new_team and joined_new_team set to NULL
                dbTransacPlayer(playerID, null, team, null);
            }
    
            // Update players to set current_team to NULL and status to 'inactive'
            pstmt2.setString(1, team);
            pstmt2.executeUpdate();
    
            // Update team to set captain, country, region to NULL and status to 'inactive'
            pstmt3.setString(1, team);
            pstmt3.executeUpdate();
    
            // Insert into teamhistory
            pstmt4.setInt(1, getRowCount("teamhistory") + 1);
            pstmt4.setString(2, team);
            pstmt4.setString(3, creationDate); // Use the retrieved creation date
            pstmt4.setString(4, date);
            pstmt4.executeUpdate();
    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dbTransacPlayer(String playerID, String date, String oldTeam, String newTeam) {
        String insertQuery = "INSERT INTO playerhistory (history_id, player_id, old_team, left_old_team, new_team, joined_new_team) " 
                            + "VALUES (?, ?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE players SET current_team = ?, status = ? WHERE player_id = ?";

        try (PreparedStatement pstmtInsert = con.prepareStatement(insertQuery);
            PreparedStatement pstmtUpdate = con.prepareStatement(updateQuery)) {

            // Insert into playerhistory
            pstmtInsert.setInt(1, getRowCount("playerhistory") + 1);
            pstmtInsert.setString(2, playerID);
            pstmtInsert.setString(3, oldTeam);
            pstmtInsert.setString(4, date);
            
            if (newTeam == null) {
                pstmtInsert.setNull(5, java.sql.Types.VARCHAR);
            } else {
                pstmtInsert.setString(5, newTeam);
            }
            
            if (date == null) {
                pstmtInsert.setNull(6, java.sql.Types.VARCHAR);
            } else {
                pstmtInsert.setString(6, date);
            }
            
            pstmtInsert.executeUpdate();

            // Update players table
            if (newTeam == null) {
                pstmtUpdate.setNull(1, java.sql.Types.VARCHAR);
            } else {
                pstmtUpdate.setString(1, newTeam);
            }
            
            pstmtUpdate.setString(2, "inactive");
            pstmtUpdate.setString(3, playerID);
            pstmtUpdate.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Error executing query: \n" + 
                                e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateComboBoxes() {
        String selected1 = (String) gui.getTeamsUpdateStatsComboBox().getSelectedItem();
        String selected2 = (String) gui.getTeamsUpdateStatsComboBox2().getSelectedItem();
    
        List<String> allItems = getIDs("teams", "team"); // Replace with your actual items
    
        List<String> itemsForComboBox1 = new ArrayList<>(allItems);
        List<String> itemsForComboBox2 = new ArrayList<>(allItems);
    
        if (selected1 != null) {
            itemsForComboBox2.remove(selected1);
        }
        if (selected2 != null) {
            itemsForComboBox1.remove(selected2);
        }
    
        gui.updateComboBox(gui.getTeamsUpdateStatsComboBoxModel(), itemsForComboBox1, selected1);
        gui.updateComboBox(gui.getTeamsUpdateStatsComboBoxModel2(), itemsForComboBox2, selected2);
    
        // Update comboBox3 based on selected1 and selected2
        gui.updateComboBox3(selected1, selected2);
    }

    private int getRowCount(String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        int rowCount = 0;
    
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMainMainPanel(), "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        return rowCount;
    }

    private ArrayList<String> getIDs(String table, String column) {
        ArrayList<String> iD = new ArrayList<>();

        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = statement.executeQuery("SELECT " + column + " FROM " + table);

            while (resultSet.next()) {
                iD.add(resultSet.getString(column));
            }

            Collections.sort(iD);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMainViewPanel(), "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        return iD;
    }

    private ArrayList<String> getIDs(String table, String column, String status) {
        ArrayList<String> iD = new ArrayList<>();

        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = statement.executeQuery("SELECT " + column + " FROM " + table + " WHERE status = '" + status + "'");

            while (resultSet.next()) {
                iD.add(resultSet.getString(column));
            }

            Collections.sort(iD);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMainViewPanel(), "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        return iD;
    }

    private ResultSet chooseRecord(String table, String name) {
        String query = "SELECT * FROM " + table + " WHERE player_id = ?";
        ResultSet rs = null;
    
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

}
