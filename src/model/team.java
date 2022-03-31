package model;

public class team {
    private int team_id;
    private String name;
    private int orgID;

    public team(int team_id, String name, int orgID) {
        this.team_id = team_id;
        this.name = name;
        this.orgID = orgID;
    }

    public int getTeamID() {
        return team_id;
    }

    public String getName() {
        return name;
    }

    public int getOrgID() {
        return orgID;
    }

    public void setTeamID(int team_id) {
        this.team_id = team_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }
}
