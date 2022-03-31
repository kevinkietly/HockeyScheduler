package model;

public class coach_since {
    private String coaching_since;
    private int team_id;

    public coach_since(String coaching_since, int team_id) {
        this.coaching_since = coaching_since;
        this.team_id = team_id;
    }

    public String getCoachingSince() {
        return coaching_since;
    }

    public void setCoachingSince(String coaching_since) {
        this.coaching_since = coaching_since;
    }

    public int getTeamID() {
        return team_id;
    }

    public void setTeamID(int team_id) {
        this.team_id = team_id;
    }
}
