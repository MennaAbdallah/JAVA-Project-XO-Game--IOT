package DTO.GameOnlineClass;

import java.io.Serializable;

public class GameHandler {
    int gameID,firstPlayerID,secondPlayerID;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getFirstPlayerID() {
        return firstPlayerID;
    }

    public void setFirstPlayerID(int firstPlayerID) {
        this.firstPlayerID = firstPlayerID;
    }

    public int getSecondPlayerID() {
        return secondPlayerID;
    }

    public void setSecondPlayerID(int secondPlayerID) {
        this.secondPlayerID = secondPlayerID;
    }
}
