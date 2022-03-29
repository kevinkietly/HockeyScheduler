package model;

public class Team {
    private int id;
    private String name;
    private int orgID;

    public Team(int id, String name, int orgID) {
        this.id = id;
        this.name = name;
        this.orgID = orgID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrgID() {
        return orgID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }
}
