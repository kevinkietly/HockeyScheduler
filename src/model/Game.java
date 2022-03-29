package model;

public class Game {
    private int id;
    private Team team1;
    private Team team2;

    public Game(int id, Team team1, Team team2) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
    }

    public int getId() {
        return id;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
