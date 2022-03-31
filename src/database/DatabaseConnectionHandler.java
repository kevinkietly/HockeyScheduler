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
import java.util.ArrayList;

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

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:stu", username, password);
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

        // Drop All Tables before setup
        dropTablesIfExist();

        //Creating a reader object
        try {
            Reader reader = new BufferedReader(new FileReader("src/sql/scripts/databaseSetup.sql"));
            sr.runScript(reader);

        } catch (FileNotFoundException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // Drop All Tables
    private void dropTablesIfExist() {
        try {
            // Get all existing table names
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            // Put all names in a list to iterate over
            ArrayList<String> tableList = new ArrayList<>();
            while(rs.next()) {
                String name = rs.getString(1).toLowerCase();
                tableList.add(name);
            }

            // Iterate backwards through the list (to avoid dropping tables that other tables depend on)
            while(!tableList.isEmpty()) {
                String name = tableList.get(tableList.size() - 1);

                query = "DROP TABLE " +name;
                ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
                ps.executeQuery();

                tableList.remove(tableList.size() - 1);
            }

            // Close ps and rs
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
