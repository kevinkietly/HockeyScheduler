package delegates;
import model.*;

public interface MainWindowDelegate {
    public void insertPlayer(goalie p);
    public void insertPlayer(defense p);
    public void insertPlayer(forward p);
    public void deleteTeam(int team_id);
    public void updatePlayerName(goalie p,String name);
    public void updatePlayerName(defense p,String name);
    public void updatePlayerName(forward p, String name);
    public String[] goaliesUnderCoachName(String name);
    public venue[] selectSpaciousVenues(int minRooms);
    public void getTeamNames();
    public void allGameParticipants();
    public void maxSeats();
    public void showCoaches();
    public void showCoachSince();
    public void showDefenses();
    public void showForwards();
    public void showGames();
    public void showGoalies();
    public void showOrganizations();
    public void showPlayers();
    public void showReferees();
    public void showRegulateGameAt();
    public void showTeams();
    public void showVenues();
}
