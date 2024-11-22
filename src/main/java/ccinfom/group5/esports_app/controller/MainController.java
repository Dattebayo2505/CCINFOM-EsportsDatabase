package ccinfom.group5.esports_app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.view.GUI;
import ccinfom.group5.esports_app.view_deprecate.OldGUI;

public class MainController implements ActionListener {
    
    private Database database;
    private OldGUI oldGUI;
    private GUI gui;

    public MainController(Database database, GUI gui) {
        this.database = database;
        this.gui = gui;     
        
        initializeTable();
    }

    public MainController(Database database, OldGUI oldGUI) {
        this.database = database;
        this.oldGUI = oldGUI;     
        
        initializeTable();
    }

    private void initializeTable() {
        // TODO: Implement TableColumnModel for companies table testing
        String[] columnNames = database.getTableColumnNameMap().get("companies");
        Object[][] data = database.getTableDataMap().get("companies");

        System.out.println("Number of rows: " + data.length);
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        gui.getMainViewTable().setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        
    }

}
