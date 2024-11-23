package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

import ccinfom.group5.esports_app.utils.*;

public class Database {
    
    private Connection con;
    private Statement statement;

    public Database() {
        this.con = null;
        this.statement = null;
    }

    public void initializeDatabase(List<String> filepaths) {
        FileReaderUtil.getDatabase(filepaths, con);
    }

    public void initiateModel(List<String> tables) {
        int i, columnCount, j, rowCount;
        String query;
        ResultSet resultSet = null;
        ResultSetMetaData metaData;
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
                String[] columnNames = new String[columnCount];

                columnNames = FileReaderUtil.setColumnNames(columnCount, metaData);

                while (resultSet.next()) {
                    tableRecord = new Object[columnCount];

                    tableRecord = FileReaderUtil.setTableRecord(columnCount, resultSet, metaData);
                    tableRecords[j] = tableRecord;
                    ++j;

                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
            
    }

    public boolean initialStatus() {
        this.con = JavaSQLConnection.tryMakeConnection();
        return this.con != null;        
    }

    public Connection getCon() {
        return con;
    }
}

