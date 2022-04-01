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
import java.util.HashMap;

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
    public void insertPlayer(forward p) {
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
    public void updatePlayerName(forward p, String name) {
        dbHandler.updatePlayerName(p,name);
    }

    @Override
    public venue[] selectSpaciousVenues(int minRooms) {
        venue[] spaciousVenues = dbHandler.selectSpaciousVenues(minRooms);
        return spaciousVenues;
    }

    @Override
    public String[] goaliesUnderCoachName(String name) {
        return dbHandler.goaliesUnderCoachName(name);
    }

    @Override
    public HashMap<Integer, Integer> maxSeatsPerRef(int ref_id) {
        return dbHandler.maxSeatsPerRef(ref_id);
    }

    @Override
    public Object[] getTeamProjection(String column) {
        return dbHandler.getTeamProjection(column);
    }

    @Override
    public String[] allGameParticipants() {
        return dbHandler.allGameParticipants();
    }

    @Override
    public int maxSeats() {
        int max = dbHandler.maxSeats();
        return max;
    }

    @Override
    public coach[] showCoaches() {
        coach[] coaches = dbHandler.getCoachInfo();
        return coaches;
    }

    @Override
    public coach_since[] showCoachSince() {
        coach_since[] coachsince = dbHandler.getCoachSinceInfo();
        return coachsince;
    }

    @Override
    public competes_in[] showCompetesIn() {
        competes_in[] competesIns = dbHandler.getCompetesIn();
        return competesIns;
    }

    @Override
    public defense[] showDefenses() {
        defense[] defenses = dbHandler.getDefenseInfo();
        return defenses;
    }

    @Override
    public forward[] showForwards() {
        forward[] forwards = dbHandler.getForwardInfo();
        return forwards;
    }

    @Override
    public game[] showGames() {
        game[] games = dbHandler.getGameInfo();
        return games;
    }

    @Override
    public goalie[] showGoalies() {
        goalie[] goalies = dbHandler.getGoalieInfo();
        return goalies;
    }

    @Override
    public organization[] showOrganizations() {
        organization[] orgs = dbHandler.getOrganizationInfo();
        return orgs;
    }

    @Override
    public player[] showPlayers() {
        player[] players = dbHandler.getPlayerInfo();
        return players;
    }

    @Override
    public referee[] showReferees() {
        referee[] referees = dbHandler.getRefereeInfo();
        return referees;
    }

    @Override
    public regulate_game_at[] showRegulateGameAt() {
        regulate_game_at[] rgas = dbHandler.getRegGameAtInfo();
        return rgas;
    }

    @Override
    public team[] showTeams() {
        team[] teams = dbHandler.getTeamInfo();
        return teams;
    }

    @Override
    public venue[] showVenues() {
        venue[] venues = dbHandler.getVenueInfo();
        return venues;
    }
}
