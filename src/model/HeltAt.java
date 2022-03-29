package model;

public class HeltAt {
    public int gameID;
    public String date;

    public HeltAt(int gameID, String date) {
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
