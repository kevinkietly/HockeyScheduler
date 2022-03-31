package model;

public class player {
    private int player_id;
    private String name;
    private int number;
    private String plays_in_since;
    private int team_id;

    public player(int player_id, String name, int number, String plays_in_since, int team_id) {
        this.player_id = player_id;
        this.name = name;
        this.number = number;
        this.plays_in_since = plays_in_since;
        this.team_id = team_id;
    }

    public int getPlayerID() {
        return player_id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getPlays_in_since() {
        return plays_in_since;
    }

    public int getTeamID() {
        return team_id;
    }

    public void setPlayerID(int player_id) {
        this.player_id = player_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPlays_in_since(String plays_in_since) {
        this.plays_in_since = plays_in_since;
    }

    public void setTeamID(int team_id) {
        this.team_id = team_id;
    }
}
