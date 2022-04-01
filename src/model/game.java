package model;

public class game {
    private int game_id;
    private int team1_score;
    private int team2_id;

    public game(int game_id, int team1_score, int team2_id) {
        this.game_id = game_id;
        this.team1_score = team1_score;
        this.team2_id = team2_id;
    }

    public int getGameID() {
        return game_id;
    }

    public int getTeam1Score() {
        return team1_score;
    }

    public int getTeam2Score() {
        return team2_id;
    }

    public void setGameID(int game_id) {
        this.game_id = game_id;
    }

    public void setTeam1ID(int team1_id) {
        this.team1_score = team1_id;
    }

    public void setTeam2ID(int team2_id) {
        this.team2_id = team2_id;
    }
}
