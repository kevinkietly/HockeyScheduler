package model;

public class referee {
    private int ref_id;
    private String name;

    public referee(int ref_id, String name) {
        this.ref_id = ref_id;
        this.name = name;
    }

    public int getRefID() {
        return ref_id;
    }

    public String getName() {
        return name;
    }

    public void setRefID(int ref_id) {
        this.ref_id = ref_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
