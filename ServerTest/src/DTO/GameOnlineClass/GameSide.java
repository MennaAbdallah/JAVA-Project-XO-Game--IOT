package DTO.GameOnlineClass;

import java.io.Serializable;

public class GameSide implements Serializable{
    Play play=null;
    boolean gamePause=false;

    public int getGameID(int sideID){
        return GameHandlersList.getGameID(sideID);
    }

    public Play getPlay() {
        return play;
    }

    //your ID
    public void setPlay(int gameID,int playerID,Play play) {
        GameHandler gameHandler = GameHandlersList.getGameHandler(gameID);
        if(gameHandler.firstPlayerID==playerID){
            GameSidesList.getGameSide(gameHandler.secondPlayerID).play=play;
        }else {
            GameSidesList.getGameSide(gameHandler.firstPlayerID).play=play;
        }
    }

    public void resetPlay(){
        play=null;
    }

    public void setWinner(int playerID){
        //TODO call database for new
        int gameID=GameHandlersList.getGameID(playerID);
        GameHandlersList.removeGameHandlerByGameID(gameID);
    }

    public void sendGamePauseReq(int gameID,int playerID){
        GameHandler gameHandler = GameHandlersList.getGameHandler(gameID);
        if(gameHandler.firstPlayerID==playerID){
            GameSidesList.getGameSide(gameHandler.secondPlayerID).gamePause=true;
        }else {
            GameSidesList.getGameSide(gameHandler.firstPlayerID).gamePause=true;
        }
    }

    public boolean hasGamePauseReq(){
        return gamePause;
    }
}
