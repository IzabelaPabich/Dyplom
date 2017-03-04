package utils;

import com.sun.rowset.CachedRowSetImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * Created by Alebazi on 2016-12-17.
 */
public class DBUtils {

    private static java.util.Properties prop = new java.util.Properties();
    private static InputStream input = null;

    //połączenie
    private static Connection connection = null;

    public static void dbConnect() throws SQLException {

        try {
            String filename = "properties";
            input = DBUtils.class.getClassLoader().getResourceAsStream(filename);

            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(prop.getProperty("jdbc_driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("Gdzie postgresql jdbc driver?");
            e.printStackTrace();
        }

        System.out.println("Udało się zarejestrować postgresql jdbc driver");

        try {
            connection = DriverManager.getConnection(prop.getProperty("conn_str"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            System.out.println("Udało się połączyć z bazą danych");
        }

    public static void dbDisconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Udało się zamknąć bazę danych");
    }

    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = connection.createStatement();

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = connection.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }

}
