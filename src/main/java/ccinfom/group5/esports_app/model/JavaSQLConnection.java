package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ccinfom.group5.esports_app.helper.DBCHelper;

class JavaSQLConnection {

    public static boolean initializeConnection(Connection con) {
        con = tryMakeConnection();
        if (con != null) {
            return true;
        }
        System.out.println("Connection failed");
        return false;
    }

    public static Connection tryMakeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + DBCHelper.serverAndPort,
                                                                DBCHelper.username, DBCHelper.password);
            System.out.println("Connection established");

            return connection;
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not Found");
            e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
