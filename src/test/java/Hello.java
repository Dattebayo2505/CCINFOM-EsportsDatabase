import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Hello {
    private JTable mainViewTable;
    private DefaultTableModel mainViewTableModel;

    public Hello() {
        // Initialize the JTable
        mainViewTable = new JTable();

        // Initialize the TableModel with some default data
        String[] columnNames = {"Column1", "Column2"};
        Object[][] data = {
            {"Data1", "Data2"},
            {"Data3", "Data4"}
        };
        mainViewTableModel = new DefaultTableModel(data, columnNames);

        // Set the TableModel to the JTable
        mainViewTable.setModel(mainViewTableModel);

        // Initialize the TableModel with some default data
        String[] column = {"2312", "124"};
        Object[][] ano = {
            {"Danat", "fabat"},
            {"legot", "abat"}
        };

        mainViewTableModel = new DefaultTableModel(ano, column);
        mainViewTable.setModel(mainViewTableModel);

    }

    public DefaultTableModel getMainViewTableModel() {
        return mainViewTableModel;
    }

    public void setMainViewTableModel(DefaultTableModel model) {
        this.mainViewTableModel = model;
        mainViewTable.setModel(model);
    }

    public static void main(String[] args) {
        // Create the GUI instance
        Hello gui = new Hello();

        // Create a JFrame to display the JTable
        JFrame frame = new JFrame("Main View Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(gui.mainViewTable));
        frame.pack();
        frame.setVisible(true);
    }
}