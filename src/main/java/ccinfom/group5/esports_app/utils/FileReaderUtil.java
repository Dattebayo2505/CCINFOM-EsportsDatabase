package ccinfom.group5.esports_app.utils;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

// import ccinfom.group5.esports_app.Driver;
import ccinfom.group5.esports_app.model.*;

public class FileReaderUtil {
    
    public static void getDatabase(List<String> filepaths, Connection con) {
        String line, mainQuery = null;
        StringBuilder query = new StringBuilder();

        for (String filepath : filepaths) {
            try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
                Statement statement = con.createStatement();
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty() || line.startsWith("-")) {
                        continue; // Skip empty lines and comments
                    }
                    query.append(line);
                    if (line.endsWith(";")) {
                        mainQuery = query.toString();
                        statement.executeUpdate(mainQuery);
                        query.setLength(0); // Clear the query
                    }
                }
            }
            catch (SQLException e) {
                GeneralUtil.debugPrint("Error executing SQL query: " + mainQuery);
                e.printStackTrace();
            } catch (IOException e) {
                GeneralUtil.debugPrint("Error reading database file: " + filepath);
                e.printStackTrace();
            }
        }

    }

    public static String[] setColumnNames(int columnCount, ResultSetMetaData metaData) throws SQLException {
        String[] columnNames = new String[columnCount];
        for (int i=0; i<columnCount; i++) {
            columnNames[i] = metaData.getColumnName(i+1);
        }
        return columnNames;
    }

    public static Object[] setTableRecord(int columnCount, ResultSet rs, ResultSetMetaData metaData) throws SQLException {
        Object[] record = new Object[columnCount];
        for (int i=0; i<columnCount; i++) {
            String columnName = metaData.getColumnName(i+1);
            String columnType = metaData.getColumnTypeName(i+1);

            switch (columnType) {
                case "INT":
                    int num = rs.getInt(columnName);
                    record[i] = rs.wasNull() ? "NULL" : String.valueOf(num);
                    break;
                case "BIGINT":
                    int num2 = rs.getInt(columnName);
                    record[i] = rs.wasNull() ? "NULL" : String.valueOf(num2);
                    break;
                case "VARCHAR":
                    String str = rs.getString(columnName);
                    record[i] = (str == null) ? "NULL" : str;
                    break;
                case "DATE":
                    String date = rs.getString(columnName);
                    record[i] = (date == null) ? "NULL" : date;
                    break;
                case "DECIMAL":
                    double dec = rs.getDouble(columnName);
                    record[i] = rs.wasNull() ? "NULL" : String.valueOf(dec);
                    break;
                case "NULL":
                    record[i] = "NULL";
                    break;
                default:
                    System.out.println("Unknown type");
                    break;
            }
        }

        return record;
    }

    public static void readConfigAndSetConnection(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line, key, value;

            key = null;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty())
                    continue;
                    
                if (line.charAt(0) == '#') 
                    key = line.substring(2);   

                
                value = br.readLine().trim();
    
                switch (key) {
                    case "password":
                        JavaSQLConnection.setPassword(value);
                        break;
                    case "username":
                        JavaSQLConnection.setUsername(value);
                        break;
                    case "dbName":
                        JavaSQLConnection.setDbName(value);
                        break;
                    case "server":
                        JavaSQLConnection.setServer(value);
                        break;
                    case "port":
                        JavaSQLConnection.setPort(value);
                        break;
                    default:
                        GeneralUtil.debugPrint("No such key: " + key);
                        break;
                }
            }
            GeneralUtil.debugPrint("Configuration loaded successfully\n");
        } catch (IOException e) {
            GeneralUtil.debugPrint("Error reading configuration file: " + filepath + "\n");
            e.printStackTrace();
        }
    }
}
