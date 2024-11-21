package ccinfom.group5.esports_app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.table.TableColumnModel;

import ccinfom.group5.esports_app.model.*;
import ccinfom.group5.esports_app.view.GUI;

public class MainController implements ActionListener {
    
    public MainController(Database database, GUI gui) {
        HashMap<String, String[]> tableColumns = database.getTableColumns();
        
        // TODO: Implement TableColumnModel for companies table testing
        TableColumnModel playerColumnModel = gui.getPlayerTable().getColumnModel();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
    }

}
