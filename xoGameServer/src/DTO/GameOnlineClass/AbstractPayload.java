/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.GameOnlineClass;

import java.io.Serializable;

/**
 *
 * @author Mashael
 */
public abstract class AbstractPayload implements Serializable {
    protected int gameID,PlayerID;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int PlayerID) {
        this.PlayerID = PlayerID;
    }
    
    
}
