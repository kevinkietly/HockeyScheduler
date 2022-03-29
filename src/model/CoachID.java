package model;

public class CoachID {
    private int id;
    private String name;
    private int teamID;

    public CoachID(int id, String name, int teamID) {
        this.id = id;
        this.name = name;
        this.teamID = teamID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
