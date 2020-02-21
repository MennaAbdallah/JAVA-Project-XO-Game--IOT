package DTO.GameOnlineClass;

import java.io.Serializable;

public class GamePlay implements Serializable {
    int gameID,PlayerID,newPlace;

    public GamePlay(){

    }
    public GamePlay(int gameID, int playerID, int newPlace) {
        this.gameID = gameID;
        PlayerID = playerID;
        this.newPlace = newPlace;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int playerID) {
        PlayerID = playerID;
    }

    public int getNewPlace() {
        return newPlace;
    }

    public void setNewPlace(int newPlace) {
        this.newPlace = newPlace;
    }
}
