package model;

public class coach {
    private int coach_id;
    private String name;
    private int team_id;

    public coach(int id, String name, int team_id) {
        this.coach_id = id;
        this.name = name;
        this.team_id = team_id;
    }

    public int getCoachID() {
        return coach_id;
    }

    public void setCoachID(int coach_id) {
        this.coach_id = coach_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamID() {
        return team_id;
    }

    public void setTeamID(int team_id) {
        this.team_id = team_id;
    }
}
