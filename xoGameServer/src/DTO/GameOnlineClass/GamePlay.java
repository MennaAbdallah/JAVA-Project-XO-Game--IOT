package DTO.GameOnlineClass;

import java.io.Serializable;

public class GamePlay extends AbstractPayload implements Serializable {
    private int newPlace;

    public GamePlay(){

    }
    public GamePlay(int gameID, int playerID, int newPlace) {
        this.gameID = gameID;
        PlayerID = playerID;
        this.newPlace = newPlace;
    }

    public int getNewPlace() {
        return newPlace;
    }

    public void setNewPlace(int newPlace) {
        this.newPlace = newPlace;
    }
}
