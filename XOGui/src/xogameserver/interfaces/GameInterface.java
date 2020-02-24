package xogameserver.interfaces;

import DTO.GameOnlineClass.GamePlay;
import DTO.GameOnlineClass.MessagePayload;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote {
    int getGameID(int playerID) throws RemoteException;
    void setPlay(GamePlay gamePlay)throws RemoteException;
    GamePlay getPlay() throws RemoteException;
    void sendMessage(MessagePayload messagePayload) throws RemoteException;
    MessagePayload getMessage() throws RemoteException;
    void resetMessage() throws RemoteException;
    void setWinner(int playerID,int newScore) throws RemoteException;;

}
