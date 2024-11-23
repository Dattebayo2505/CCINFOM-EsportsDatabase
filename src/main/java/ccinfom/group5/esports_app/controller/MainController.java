package ccinfom.group5.esports_app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.utils.FileReaderUtil;
import ccinfom.group5.esports_app.view.GUI;
import ccinfom.group5.esports_app.view_deprecate.OldGUI;

public class MainController implements ActionListener {
    
    private Database database;
    private OldGUI oldGUI;
    private GUI gui;
    private Connection con;
    private Statement statement;

    public MainController(Database database, GUI gui, Connection con) {
        this.database = database;
        this.gui = gui;
        this.con = con;     
        
        gui.addClickListener(this);

        initializeTable();
    }

    public MainController(Database database, OldGUI oldGUI) {
        this.database = database;
        this.oldGUI = oldGUI;     
        
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
        String query = gui.getQueryMainViewTxtField().getText();
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

            for (i=0; i<columnCount; i++) {
                columnNames[i] = metaData.getColumnName(i+1);
            }

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


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Main Menu Page
        if (source == gui.getMainViewBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainview");
        }
        // else if (source == gui.getMakeTransacBtn()) {
        //     gui.getCardLayout().show(gui.getMainMainPanel(), "maketransac");
        // }
        // else if (source == gui.getGenReportsBtn()) {
        //     gui.getCardLayout().show(gui.getMainMainPanel(), "genreports");
        // }
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
        else if (source == gui.getMainViewMainMenuBtn()) {
            gui.getCardLayout().show(gui.getMainMainPanel(), "mainmenu");
        }




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

}
