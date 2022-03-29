package model;

public class Player {
    private int id;
    private String name;
    private int number;
    private String plays_in_since;
    private int teamID;

    public Player(int id, String name, int number, String plays_in_since, int teamID) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.plays_in_since = plays_in_since;
        this.teamID = teamID;
    }

    public int getId() {
        return id;
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
        return teamID;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
