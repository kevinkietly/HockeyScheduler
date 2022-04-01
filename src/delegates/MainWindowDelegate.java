package delegates;

import model.*;

import java.util.HashMap;

public interface MainWindowDelegate {
    public void insertPlayer(goalie p);
    public void insertPlayer(defense p);
    public void insertPlayer(forward p);
    public void deleteTeam(int team_id);
    public void updatePlayerName(goalie p,String name);
    public void updatePlayerName(defense p,String name);
    public void updatePlayerName(forward p, String name);
    public String[] goaliesUnderCoachName(String name);
    public HashMap<Integer, Integer> maxSeatsPerRef();
    public venue[] selectSpaciousVenues(int minRooms);
    public Object[] getTeamProjection(String column);
    public String[] allGameParticipants();
    public int maxSeats();
    public coach[] showCoaches();
    public coach_since[] showCoachSince();
    public competes_in[] showCompetesIn();
    public defense[] showDefenses();
    public forward[] showForwards();
    public game[] showGames();
    public goalie[] showGoalies();
    public organization[] showOrganizations();
    public player[] showPlayers();
    public referee[] showReferees();
    public regulate_game_at[] showRegulateGameAt();
    public team[] showTeams();
    public venue[] showVenues();

}
