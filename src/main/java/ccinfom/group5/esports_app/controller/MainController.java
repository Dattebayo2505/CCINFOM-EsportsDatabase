package ccinfom.group5.esports_app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.utils.FileReaderUtil;
import ccinfom.group5.esports_app.utils.GeneralUtil;
import ccinfom.group5.esports_app.view.GUI;

public class MainController implements ActionListener {
    
    private GUI gui;
    private Connection con;
    private Statement statement;


    public MainController(Database database, GUI gui, Connection con) {
        this.gui = gui;
        this.con = con;     
        
        gui.addClickListener(this);
        
        gui.setPlayersComboBoxModel(getIDs("players", "player_id", "active"));
        gui.setTeamsComboBoxModel(getIDs("teams", "team", "active"));
        gui.setSponsorsComboBoxModel(getIDs("companies", "company"));

        gui.getMainViewTable().setModel(initializeTable("players"));
        gui.getGenReportsTable().setModel(initializeTable("playerhistory"));
    }

    private DefaultTableModel initializeTable(ResultSet rs) {
        String[] columnNames = null;
        Object[][] data = null;
        ResultSet rs2;
        ResultSetMetaData metaData;
        int columnCount, rowCount, i;

        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

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

    private void doExecuteQuery() {
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
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            boolean isResultSet = statement.execute(query);
    
                ResultSet resultSet = statement.getResultSet();
                gui.getMainViewTable().setModel(initializeTable(resultSet));


            JOptionPane.showMessageDialog(gui.getMainViewPanel(), "Query executed successfully.", 
                            "Query Success", JOptionPane.INFORMATION_MESSAGE);
                            
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
            setMainViewTableView();
            String selected = (String) gui.getTablesMainViewComboBox().getSelectedItem();

            if (selected.equals("players") 
                || selected.equals("teams")) {

                gui.setGeneralComboBoxModel(getIDs(selected, 1));
            }
            if (selected.equals("companies")) {
                gui.setGeneralComboBoxModel(getIDs(selected, 2));
            }
        }

        else if (source == gui.getInsertRecordMainViewBtn()) {
            String table = (String) gui.getTablesMainViewComboBox().getSelectedItem();
            List<String> columnNames = getColumnNames(table);
            String columns = String.join(", ", columnNames);
            StringBuilder placeholders = new StringBuilder();
            for (int i = 0; i < columnNames.size(); i++) {
                placeholders.append("<>");
                if (i < columnNames.size() - 1) {
                    placeholders.append(", ");
                }
            }
            String query = "INSERT INTO " + table + "\n(" + columns + ") VALUES\n(" + placeholders.toString() + ")";
    
            gui.getQueryMainViewTxtArea().setText(query);
        }
        else if (source == gui.getUpdateRecordMainViewBtn()) {
            String table = (String) gui.getTablesMainViewComboBox().getSelectedItem();
            String query = "UPDATE " + table + "\nSET <> \nWHERE\n" +
                    "<> = <>"; 
    
            gui.getQueryMainViewTxtArea().setText(query);
        }
        else if (source == gui.getDeleteRecordMainViewBtn()) {
            String table = (String) gui.getTablesMainViewComboBox().getSelectedItem();
            String query = "DELETE FROM " + table + "\nWHERE\n" + 
                    "<> = <>"; 
    
            gui.getQueryMainViewTxtArea().setText(query);
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
        }   

        else if(source == gui.getFinalAddSponsorTeamBtn()) {
            doAddSponsor();
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

                gui.setTeamsComboBoxModel(getIDs("teams", "team", "active"));

                JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Team dissolved successfully.", 
                "Dissolve Success", JOptionPane.INFORMATION_MESSAGE);
                
                
                
                setMainViewTableView();
            }
        }       
        else if (source == gui.getTeamsUpdateStatsComboBox() || 
                 source == gui.getTeamsUpdateStatsComboBox2()) {

            updateComboBoxes();
        }
        else if (source == gui.getFinalTeamsUpdateStatsBtn()) {
            dpUpdateTeamStats();
        }

        else if (source == gui.getMainMenuTransacBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainmenu");
        }

        else if (source == gui.getGenReportsToggleBtn()) {
            if (gui.getGenReportsToggleBtn().isSelected()) {
                gui.getGenReportsToggleBtn().setText("<html><div style='text-align': center;'>MONTHS<br>ACTIVATED</div></html>");
            } else {
                gui.getGenReportsToggleBtn().setText("<html><div style='text-align': center;'>MONTHS<br>DEACTIVATED</div></html>");
            }
        }
        // Generate Reports Page
        else if (source == gui.getFinalGenReportsBtn()) {
            try {
                int year = Integer.parseInt(gui.getYearGenReportsTxtField().getText());
                Integer month = gui.getGenReportsToggleBtn().isSelected() ? gui.getMonthGenReportsComboBox().getSelectedIndex() + 1 : null;
        
                if (gui.getTablesGenReportsComboBox().getSelectedIndex() == 0)
                    viewTransferReport(year, month);
                if (gui.getTablesGenReportsComboBox().getSelectedIndex() == 1)
                    viewCreationDeletionReport(year, month);
                if (gui.getTablesGenReportsComboBox().getSelectedIndex() == 2)
                    viewSponsorshipSummaryReport(year, month);
                if (gui.getTablesGenReportsComboBox().getSelectedIndex() == 3)
                    viewTeamPerformanceReport(year, month);
        
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        else if (source == gui.getGenReportsMainMenuBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainmenu");
        }

    }

    public List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        try {
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, tableName, null);
            while (rs.next()) {
                columnNames.add(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }

    public void viewTransferReport(int year, Integer month) throws SQLException {
        String preQuery = "SET @month = " + (month != null ? month : "NULL") + ";";
        String query = "SELECT " +
                    "COUNT(ph.history_id) AS total_transfers, " +
                    "COUNT(ph.history_id) / COUNT(DISTINCT ph.player_id) AS avg_transfers_per_player " +
                    "FROM playerhistory ph " +
                    "WHERE YEAR(ph.joined_new_team) = " + year + 
                    " AND (MONTH(ph.joined_new_team) = @month OR @month IS NULL)" +
                    " GROUP BY MONTH(ph.joined_new_team)";

        ResultSet rs = null;
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    ResultSet.CONCUR_READ_ONLY)) {
            stmt.execute(preQuery);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int totalTransfers = rs.getInt("total_transfers");
                double avgTransfersPerPlayer = rs.getDouble("avg_transfers_per_player");

                GeneralUtil.debugPrint("Total Transfers: " + totalTransfers + "\n" +
                "Average Transfers per Player: " + avgTransfersPerPlayer + "\n");
            }
    
            gui.getGenReportsTable().setModel(initializeTable(rs));
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewCreationDeletionReport(int year, Integer month) throws SQLException {
        String preQuery = "SET @month = " + (month != null ? month : "NULL") + ";";
        String query = "SELECT " +
                "MONTH(th.creation_date) AS month, " +
                "COALESCE(COUNT(DISTINCT CASE WHEN YEAR(th.creation_date) = " + year + 
                " AND MONTH(th.creation_date) = @month " + 
                " THEN th.team END), 0) AS teams_created, " +
                "COALESCE(COUNT(DISTINCT CASE WHEN YEAR(th.disband_date) = " + year + 
                " AND MONTH(th.disband_date) = @month " + 
                " THEN th.team END), 0) AS teams_disbanded, " +
                "COALESCE(ROUND(AVG(CASE WHEN YEAR(th.creation_date) = " + year + 
                " AND MONTH(th.creation_date) = @month " + 
                " THEN 1 ELSE NULL END), 2), 0) AS avg_teams_created, " +
                "COALESCE(ROUND(AVG(CASE WHEN YEAR(th.disband_date) = " + year + 
                " AND MONTH(th.disband_date) = @month " + 
                " THEN 1 ELSE NULL END), 2), 0) AS avg_teams_disbanded, " +
                "COALESCE(COUNT(DISTINCT ph.player_id), 0) AS players_affected_by_disband " +
                "FROM teamhistory th " +
                "LEFT JOIN playerhistory ph ON th.team = ph.old_team " +
                "AND ph.left_old_team = th.disband_date " +
                "WHERE (YEAR(th.creation_date) = " + year + " AND MONTH(th.creation_date) = @month) " +
                "OR (YEAR(th.disband_date) = " + year + " AND MONTH(th.disband_date) = @month) " +
                "GROUP BY month;";
    
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    ResultSet.CONCUR_READ_ONLY)) {
            stmt.execute(preQuery);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int teamsCreated = rs.getInt("teams_created");
                int teamsDisbanded = rs.getInt("teams_disbanded");
                double avgTeamsCreated = rs.getDouble("avg_teams_created");
                double avgTeamsDisbanded = rs.getDouble("avg_teams_disbanded");
                int playersAffectedByDisband = rs.getInt("players_affected_by_disband");

                GeneralUtil.debugPrint("Teams Created: " + teamsCreated + "\n" +
                "Teams Disbanded: " + teamsDisbanded + "\n" +
                "Average Teams Created: " + avgTeamsCreated + "\n" +
                "Average Teams Disbanded: " + avgTeamsDisbanded + "\n" +
                "Players Affected by Disband: " + playersAffectedByDisband + "\n");
            }
            gui.getGenReportsTable().setModel(initializeTable(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewSponsorshipSummaryReport(int year, Integer month) throws SQLException {
        String preQuery = "SET @month = " + (month != null ? month : "NULL") + ";";
        String query = "SELECT " +
                       "MONTH(COALESCE(s.contract_start, h.contract_start)) AS sponsorship_month, " +
                       "t.team, " +
                       "SUM( " +
                           "CASE " +
                               "WHEN YEAR(COALESCE(s.contract_start, h.contract_start)) = YEAR(CURRENT_DATE) " +
                               "THEN s.contract_amount " +
                               "ELSE h.contract_amount " +
                           "END " +
                       ") AS total_sponsorship, " +
                       "AVG( " +
                           "CASE " +
                               "WHEN YEAR(COALESCE(s.contract_start, h.contract_start)) = YEAR(CURRENT_DATE) " +
                               "THEN s.contract_amount " +
                               "ELSE h.contract_amount " +
                           "END " +
                       ") AS average_sponsorship, " +
                       "COUNT( " +
                           "CASE " +
                               "WHEN YEAR(COALESCE(s.contract_start, h.contract_start)) = YEAR(CURRENT_DATE) " +
                               "THEN s.sponsor_id " +
                               "ELSE h.sponsor_id " +
                           "END " +
                       ") AS total_sponsors " +
                   "FROM " +
                       "teams t " +
                   "LEFT JOIN " +
                       "teamsponsor s ON t.team = s.team " +
                   "LEFT JOIN " +
                       "sponsorhistory h ON t.team = h.team " +
                       "AND YEAR(h.contract_start) < YEAR(CURRENT_DATE) " +
                   "WHERE " +
                       "YEAR(COALESCE(s.contract_start, h.contract_start)) = " + year + " " +
                       "AND (@month IS NULL OR MONTH(COALESCE(s.contract_start, h.contract_start)) = @month) " +
                   "GROUP BY " +
                       "t.team, " +
                       "YEAR(COALESCE(s.contract_start, h.contract_start)), " +
                       "MONTH(COALESCE(s.contract_start, h.contract_start)) " +
                   "ORDER BY " +
                       "sponsorship_month, " +
                       "t.team;";
            
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
        ResultSet.CONCUR_READ_ONLY)) {
            stmt.execute(preQuery);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String team = rs.getString("team");
                double totalSponsorship = rs.getDouble("total_sponsorship");
                double averageSponsorship = rs.getDouble("average_sponsorship");
                int totalSponsors = rs.getInt("total_sponsors");

                GeneralUtil.debugPrint("Team: " + team + "\n" +
                "Total Sponsorship: " + totalSponsorship + "\n" +
                "Average Sponsorship: " + averageSponsorship + "\n" +
                "Total Sponsors: " + totalSponsors + "\n");
            }
            gui.getGenReportsTable().setModel(initializeTable(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewTeamPerformanceReport(int year, Integer month) {
        String setMonthQuery = "SET @month = " + (month != null ? month : "NULL");
        String selectQuery = "SELECT tph.team, " +
                "GROUP_CONCAT(DISTINCT MONTH(tph.match_date) ORDER BY MONTH(tph.match_date)) AS match_months, " +
                "SUM(tph.winnings) AS total_winnings, " +
                "COUNT(CASE WHEN tph.result = 'win' THEN 1 END) AS total_wins " +
                "FROM teamperformancehistory tph " +
                "WHERE YEAR(tph.match_date) = 2023 " +
                "AND (@month IS NULL OR MONTH(tph.match_date) = @month) " +
                "GROUP BY tph.team " +
                "ORDER BY tph.team";
                
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    ResultSet.CONCUR_READ_ONLY)) {
            // Execute the SET query
            stmt.execute(setMonthQuery);
    
            // Execute the SELECT query
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                String team = rs.getString("team");
                double totalWinnings = rs.getDouble("total_winnings");
                int totalWins = rs.getInt("total_wins");

                GeneralUtil.debugPrint("Team: " + team + "\n" +
                "Total Winnings: " + totalWinnings + "\n" +
                "Total Wins: " + totalWins + "\n");
            }
            gui.getGenReportsTable().setModel(initializeTable(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Sponsor added successfully.", 
                            "Add Sponsor Success", JOptionPane.INFORMATION_MESSAGE);
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Error executing query: \n" + 
                            ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        dpAddSponsor(team, numSponsor);
    }

    private void dpAddSponsor(String team, int numSponsor) {
        String yearStart = gui.getYearSponsorAddTxtField().getText();
        String monthStart = gui.getMonthSponsorAddTxtField().getText();
        String dayStart = gui.getDaySponsorAddTxtField().getText();
        
        String yearEnd = gui.getYearSponsorAddTxtField1().getText();
        String monthEnd = gui.getMonthSponsorAddTxtField1().getText();
        String dayEnd = gui.getDaySponsorAddTxtField1().getText();
        
        if (yearStart.isEmpty() || monthStart.isEmpty() || dayStart.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Start date fields cannot be empty.", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (yearEnd.isEmpty() || monthEnd.isEmpty() || dayEnd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "End date fields cannot be empty.", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String dateStart = yearStart + "-" + monthStart + "-" + dayStart;
        String dateEnd = yearEnd + "-" + monthEnd + "-" + dayEnd;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(dateStart, dateFormatter);
        LocalDate endDate = LocalDate.parse(dateEnd, dateFormatter);
    
        if (startDate.isAfter(endDate)) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Start date cannot be later than end date.", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        } 

        String sponsor = (String) gui.getTeamSponsorAddComboBox().getSelectedItem();

        int contractAmount = (int) gui.getAddSponsorTeamSpinner().getValue();

        if (numSponsor == 3) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Team has reached MAX sponsors.", 
                            "Sponsor Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String getSponsorIDFromNameQuery = "SELECT company_id FROM companies WHERE company = ?";
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
            
            int companyID = -1;
            if (rs.next()) {
                companyID = rs.getInt("company_id");        

                pstmt1.setInt(1, getRowCount("teamsponsor") + 1);
                pstmt1.setInt(2, companyID);
                pstmt1.setInt(3, contractAmount);
                pstmt1.setString(4, dateStart);
                pstmt1.setString(5, dateEnd);
        
                pstmt2.setInt(1, getRowCount("sponsorhistory") + 1);
                pstmt2.setInt(2, companyID);
                pstmt2.setString(3, team);
                pstmt2.setInt(4, contractAmount);
                pstmt2.setString(5, dateStart);
                pstmt2.setString(6, dateEnd);
        
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
            } else {
                GeneralUtil.debugPrint("Sponsor not found" + companyID);
                JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Sponsor not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Error executing query: \n" + 
                            e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }        

    private void dpUpdateTeamStats() {
        String year = gui.getYearTeamsUpdateStatsTxtField().getText();
        String month = gui.getMonthTeamsUpdateStatsTxtField().getText();
        String day = gui.getDayTeamsUpdateStatsTxtField().getText();

        if (year.isEmpty() || month.isEmpty() || day.isEmpty()) {
            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "All date fields must be filled out.", 
                            "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String date = year + "-" + month + "-" + day;

        String team1 = (String) gui.getTeamsUpdateStatsComboBox().getSelectedItem();
        String team2 = (String) gui.getTeamsUpdateStatsComboBox2().getSelectedItem();
        String winner = (String) gui.getTeamsWinnerUpdateStatsComboBox().getSelectedItem();

        int winnings = (int) gui.getTeamsUpdateStatsSpinner().getValue();


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

            setMainViewTableView();

            JOptionPane.showMessageDialog(gui.getMakeTransacPanel(), "Team stats updated successfully.", 
                            "Update Success", JOptionPane.INFORMATION_MESSAGE);

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

    private ArrayList<String> getIDs(String table, int columnIndex) {
        ArrayList<String> iD = new ArrayList<>();
    
        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
    
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
    
            while (resultSet.next()) {
                iD.add(resultSet.getString(columnIndex));
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
