package model;

public class team {
    private int team_id;
    private String name;
    private int org_id;

    public team(int team_id, String name, int org_id) {
        this.team_id = team_id;
        this.name = name;
        this.org_id = org_id;
    }

    public int getTeamID() {
        return team_id;
    }

    public String getName() {
        return name;
    }

    public int getOrgID() {
        return org_id;
    }

    public void setTeamID(int team_id) {
        this.team_id = team_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrgID(int org_id) {
        this.org_id = org_id;
    }
}
