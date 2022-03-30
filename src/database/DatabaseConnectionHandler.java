package database;

import org.apache.ibatis.jdbc.ScriptRunner;
import util.PrintablePreparedStatement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnectionHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private Connection connection;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public boolean login(String username, String password) throws SQLException, FileNotFoundException {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:stu", "ora_dylanw11", "a29033891");
            //connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:stu", username, password);
            connection.setAutoCommit(false);
            System.out.println("\nConnected to Oracle!");
            databaseSetup();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void databaseSetup() {
        ScriptRunner sr = new ScriptRunner(connection);
        //Creating a reader object
        try {
            Reader reader = new BufferedReader(new FileReader("src/sql/scripts/databaseSetup.sql"));
            sr.runScript(reader);
        } catch (FileNotFoundException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    private void dropTablesIfExist() {
        try {
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String name = rs.getString(1).toLowerCase();
                ps. execute("DROP TABLE "+name);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
