package ccinfom.group5.esports_app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.model.tables.*;
import ccinfom.group5.esports_app.utils.FileReaderUtil;
import ccinfom.group5.esports_app.view.GUI;
import ccinfom.group5.esports_app.view_deprecate.OldGUI;

public class MainController implements ActionListener {
    
    private Database database;
    private OldGUI oldGUI;
    private GUI gui;
    private Connection con;
    private Statement statement;
    private Transaction transaction;

    public MainController(Database database, GUI gui, Connection con) {
        this.database = database;
        this.gui = gui;
        this.con = con;     
        
        transaction = new Transaction(database);

        gui.addClickListener(this);

        gui.setPlayerNames(getIDs("players", "player_id"));
        gui.setTeamNames(getIDs("teams", "team"));
        gui.setSponsorNames(getIDs("companies", "company"));

        gui.updatePlayersComboBoxModel();
        gui.updateTeamsComboBoxModel();
        // TODO add gui updatesponsorcomboboxmodel

        initializeTable();
    }

    private void initializeTable() {
        String[] columnNames = database.getTableColumnNameMap().get("players");
        Object[][] data = database.getTableDataMap().get("players");

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        gui.getMainViewTable().setModel(model);
    }

    private void doRefreshTable() {
        String selectedTable = (String) gui.getTablesMainViewComboBox().getSelectedItem();
        initializeTable(selectedTable);
    }

    private void initializeTable(String tableName) {
        String[] columnNames = database.getTableColumnNameMap().get(tableName);
        Object[][] data = database.getTableDataMap().get(tableName);
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        gui.getMainViewTable().setModel(model);

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
            JOptionPane.showMessageDialog(null, "Query cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (isSemicolonPresent(query)) {
            JOptionPane.showMessageDialog(null, "Multiple queries not allowed", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void doTransferPlayer() {
        String playerID = (String) gui.getPlayerTransferPlayerComboBox().getSelectedItem();
        String newTeam = (String) gui.getTeamTransferPlayerTransacComboBox().getSelectedItem();
        Player player = choosePlayer(playerID);

        if (player.getCurrentTeam().equals(newTeam)) {
            JOptionPane.showMessageDialog(null, "The player is already in the selected team.", "Transfer Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder(gui.getYearTransferPlayerTxtField().toString());

        sb.append("-");
        sb.append(gui.getMonthTransferPlayerTxtField().toString());
        sb.append("-");
        sb.append(gui.getDayTransferPlayerTxtField().toString());

        transaction.playerTransfer(player, sb.toString(), newTeam, sb.toString());

        JOptionPane.showMessageDialog(null, "Player transferred successfully.", "Transfer Success", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
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
            System.out.println("Selected item: " + selectedTable);

            initializeTable(selectedTable);
        }
        else if (source == gui.getMakeTransacBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "maketransac");
        }
        else if (source == gui.getMainViewMainMenuBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainmenu");
        }

        // Make Transaction Page
        else if (source == gui.getFinalTransferPlayerBtn()) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to transfer the player?", "Confirm Transfer", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                doTransferPlayer();
            }

            gui.getRefreshTableTransferPlayerBtn().setVisible(true);
        }
        else if (source == gui.getRefreshTableTransferPlayerBtn()) {
            // TODO INSERT HERE ALGO
            
            doRefreshTable();
            gui.getRefreshTableTransferPlayerBtn().setVisible(false);
        }
        else if (source == gui.getMainMenuTransacBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainmenu");
        }

        
        // Generate Reports Page




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
            JOptionPane.showMessageDialog(null, "Error executing query: \n" + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        return iD;
    }

    private Player choosePlayer(String name) {
        for (Player player : database.getAllPlayers()) {
            if (player.getPlayerID().equals(name)) {
                return player;
            }
        }
        return null;
    }

}
