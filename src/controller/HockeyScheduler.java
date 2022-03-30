package controller;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.MainWindowDelegate;
import ui.LoginWindow;
import ui.MainWindow;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class HockeyScheduler implements LoginWindowDelegate, MainWindowDelegate {
    private DatabaseConnectionHandler dbHandler;
    private LoginWindow loginWindow;

    public static void main(String[] args) {
        FlatNordIJTheme.install();
        HockeyScheduler hockeyScheduler = new HockeyScheduler();
        hockeyScheduler.initiate();
    }

    public HockeyScheduler() {
        dbHandler = new DatabaseConnectionHandler();
    }

    private void initiate() {
        loginWindow = new LoginWindow(this);
    }

    public void login(String username, String password) throws FileNotFoundException, SQLException {
        boolean isConnected = dbHandler.login(username, password);
        if (isConnected) {
            loginWindow.dispose();
            MainWindow mainWindow = new MainWindow();
        } else {
            loginWindow.handleLoginFailed();
        }
    }

    @Override
    public void databaseSetup() {
        dbHandler.databaseSetup();
    }
}
