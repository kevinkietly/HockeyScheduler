package model;

public class organization {
    private int org_id;
    private String name;
    private String city;

    public organization(int org_id, String name, String city) {
        this.org_id = org_id;
        this.name = name;
        this.city = city;
    }

    public int getOrgID() {
        return org_id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setOrgID(int org_id) {
        this.org_id = org_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static class competes_in {
    }
}
