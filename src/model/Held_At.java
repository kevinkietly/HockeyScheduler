package model;

public class Held_At {
    public int gameID;
    public String date;

    public Held_At(int gameID, String date) {
        this.gameID = gameID;
        this.date = date;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
