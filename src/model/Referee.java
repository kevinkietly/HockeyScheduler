package model;

public class Referee {
    private int id;
    private String name;

    public Referee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
