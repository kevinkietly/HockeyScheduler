package controller;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.MainWindowDelegate;
import model.*;
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
            MainWindow mainWindow = new MainWindow(this);
        } else {
            loginWindow.handleLoginFailed();
        }
    }

    @Override
    public void insertPlayer(goalie p) {
        dbHandler.insertPlayer(p);
    }

    @Override
    public void insertPlayer(defense p) {
        dbHandler.insertPlayer(p);
    }

    @Override
    public void insertPlayer(Forward p) {
        dbHandler.insertPlayer(p);
    }

    @Override
    public void deleteTeam(int team_id) {
        dbHandler.deleteTeam(team_id);
    }

    @Override
    public void updatePlayerName(goalie p,String name) {
        dbHandler.updatePlayerName(p,name);
    }

    @Override
    public void updatePlayerName(defense p,String name) {
        dbHandler.updatePlayerName(p,name);
    }

    @Override
    public void updatePlayerName(Forward p,String name) {
        dbHandler.updatePlayerName(p,name);
    }

    @Override
    public void selectSpaciousVenues(int minRooms) {
        venue[] spaciousVenues = dbHandler.selectSpaciousVenues(minRooms);
        //Link up with GUI implementation
    }

    @Override
    public void getTeamNames() {
        String[] names = dbHandler.getTeamNames();
        //Link up with GUI implementation
    }

    @Override
    public void allGameParticipants() {
        dbHandler.allGameParticipants();
    }

    @Override
    public void maxSeats() {
        int max = dbHandler.maxSeats();
        //Link up eiyh GUI implementation
    }

    @Override
    public void showCoaches() {
        dbHandler.getCoachInfo();
    }

    @Override
    public void showCoachSince() {
        coach[] coaches = dbHandler.getCoachInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showDefenses() {
        defense[] defenses = dbHandler.getDefenseInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showForwards() {
        Forward[] forwards = dbHandler.getForwardInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showGames() {
        game[] games = dbHandler.getGameInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showGoalies() {
        goalie[] goalies = dbHandler.getGoalieInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showOrganizations() {
        organization[] orgs = dbHandler.getOrganizationInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showPlayers() {
        player[] players = dbHandler.getPlayerInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showReferees() {
        referee[] referees = dbHandler.getRefereeInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showRegulateGameAt() {
        regulate_game_at[] rgas = dbHandler.getRegGameAtInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showTeams() {
        team[] teams = dbHandler.getTeamInfo();
        // Link up with GUI implementation
    }

    @Override
    public void showVenues() {
        venue[] venues = dbHandler.getVenueInfo();
        // Link up with GUI implementation
    }
}
