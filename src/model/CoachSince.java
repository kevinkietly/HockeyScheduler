package model;

public class CoachSince {
    private String coachingSince;
    private int teamID;

    public CoachSince(String coachingSince, int teamID) {
        this.coachingSince = coachingSince;
        this.teamID = teamID;
    }

    public String getCoachingSince() {
        return coachingSince;
    }

    public void setCoachingSince(String coachingSince) {
        this.coachingSince = coachingSince;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
