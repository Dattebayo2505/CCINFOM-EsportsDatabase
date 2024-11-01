package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ccinfom.group5.esports_app.utils.*;

public class JavaSQLConnection {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String password;
    private static String username;
    private static String dbName;
    private static String server;
    private static String port;

    public static boolean initializeConnection(Connection con) {
        con = tryMakeConnection();
        if (con != null) {
            return true;
        }
        GeneralUtil.debugPrint("Connection failed");
        return false;
    }

    public static Connection tryMakeConnection() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port,
                                                                username, password);
            GeneralUtil.debugPrint("Connection established");

            return connection;
        }
        catch (ClassNotFoundException e) {
            GeneralUtil.debugPrint("Class not Found\n");
            e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDbName() {
        return dbName;
    }

    public static void setPassword(String password) {
        JavaSQLConnection.password = password;
    }

    public static void setUsername(String username) {
        JavaSQLConnection.username = username;
    }

    public static void setDbName(String dbName) {
        JavaSQLConnection.dbName = dbName;
    }

    public static void setServer(String server) {
        JavaSQLConnection.server = server;
    }

    public static void setPort(String port) {
        JavaSQLConnection.port = port;
    }


}
