package model;

public class Venue {
    private int id;
    private String address;
    private int numRooms;
    private int numSeats;
    private int numRinks;

    public Venue(int id, String address, int numRooms, int numSeats, int numRinks) {
        this.id = id;
        this.address = address;
        this.numRooms = numRooms;
        this.numSeats = numSeats;
        this.numRinks = numRinks;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public int getNumRinks() {
        return numRinks;
    }
}