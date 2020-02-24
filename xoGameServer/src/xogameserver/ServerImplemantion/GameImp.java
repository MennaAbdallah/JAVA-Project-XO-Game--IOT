package xogameserver.ServerImplemantion;


import DTO.GameOnlineClass.GameHandlersList;
import DTO.GameOnlineClass.GamePlay;
import DTO.GameOnlineClass.MessagePayload;
import xogameserver.interfaces.GameInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.DBManagment;

public class GameImp extends UnicastRemoteObject implements GameInterface {
    private DBManagment dbConnection= DBManagment.getInstance();
    private GamePlay gamePlay=new GamePlay();
  
    
    public GameImp() throws RemoteException {

    }

    @Override
    public int getGameID(int playerID) throws RemoteException {
        return GameHandlersList.getGameID(playerID);
    }

    @Override
    public void setPlay(GamePlay gamePlay) throws RemoteException {
        System.out.println(gamePlay.getNewPlace());
        System.out.println(gamePlay.getGameID());
        System.out.println(gamePlay.getPlayerID());

        this.gamePlay=gamePlay;
    }

    @Override
    public GamePlay getPlay() throws RemoteException {
        return gamePlay;
    }
    
    @Override
    public void setWinner(int playerID ,int newScore) throws RemoteException {
        dbConnection.setScore(playerID, newScore);
    }

    
}
