package model;

public class competes_in {
    private int team_id;
    private int game_id;

    public competes_in(int team_id,int game_id) {
        this.team_id = team_id;
        this.game_id = game_id;
    }

    public int getTeamID() {
        return team_id;
    }

    public void setTeamID(int team_id) {
        this.team_id = team_id;
    }

    public int getGameID() {
        return game_id;
    }

    public void setGameID(int gane_id) {
        this.team_id = game_id;
    }
}
