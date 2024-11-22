package ccinfom.group5.esports_app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.view.GUI;

public class MainController implements ActionListener {
    
    private Database database;
    private GUI gui;

    public MainController(Database database, GUI gui) {
        this.database = database;
        this.gui = gui;     
        
        initializeTable();
    }

    private void initializeTable() {
        // TODO: Implement TableColumnModel for companies table testing
        String[] columnNames = database.getTableColumnNameMap().get("companies");
        Object[][] data = database.getTableDataMap().get("companies");

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
