package DTO.GameOnlineClass;

import xogameserver.interfaces.gameInterface;

public class gameImp implements gameInterface {
    public void addGameSide(int sideID, GameSide gameSide) {
        GameSidesList.addGameSide(sideID,gameSide);
    }
}
