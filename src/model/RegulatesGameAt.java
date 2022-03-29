package model;

public class RegulatesGameAt {
    private int gameID;
    private int refID;
    private int venueID;
    private String date;

    public RegulatesGameAt(int gameID, int refID, int venueID, String date) {
        this.gameID = gameID;
        this.refID = refID;
        this.venueID = venueID;
        this.date = date;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getRefID() {
        return refID;
    }

    public void setRefID(int refID) {
        this.refID = refID;
    }

    public int getVenueID() {
        return venueID;
    }

    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
