/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.ServerImplemantion;

import DTO.GameOnlineClass.GameHandler;
import DTO.GameOnlineClass.GameHandlersList;
import DTO.InvitationPayload;
import DTO.SimpleUser;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import xogameserver.DBManagment;
import xogameserver.interfaces.InvitationInterface;

/**
 *
 * @author Mashael
 */
public class InvitationImp extends UnicastRemoteObject implements InvitationInterface{

    private static final int SEC_NEW_INVITE=-1,SEC_REFUSE_INVITE=-2;
    
    DBManagment dbm = DBManagment.getInstance();
    InvitationPayload invitationPayload;
    int gameID=0;
    
    public InvitationImp()throws RemoteException{
        
    }
    @Override
    public Vector<SimpleUser> getUsers() throws RemoteException {
        return dbm.getAllUsersStatus();
    }

    @Override
    public int sendInvitation(int senderID, String senderName, int recID) throws RemoteException {
        gameID++;
        
        GameHandler gameHandler = new GameHandler();
        gameHandler.setGameID(gameID);
        gameHandler.setFirstPlayerID(senderID);
        gameHandler.setSecondPlayerID(SEC_NEW_INVITE);
        GameHandlersList.addGameHandler(gameHandler);
        
        invitationPayload = new InvitationPayload();
        invitationPayload.setGameID(gameID);
        invitationPayload.setRecID(recID);
        invitationPayload.setSenderName(senderName);
        
        return gameID;
    }

    @Override
    public void acceptInvitation(int gameID, int recID) throws RemoteException {
        GameHandler gameHandler = GameHandlersList.getGameHandler(gameID);
        gameHandler.setSecondPlayerID(recID);
    }

    @Override
    public void refuseInvitation(int gameID, int recID) throws RemoteException {
        GameHandler gameHandler = GameHandlersList.getGameHandler(gameID);
        gameHandler.setSecondPlayerID(SEC_REFUSE_INVITE);
    }

    @Override
    public InvitationPayload getNewInvitation() throws RemoteException {
        return invitationPayload;
    }

    @Override
    public GameHandler getMyInvitationState(int gameID) throws RemoteException {
        return GameHandlersList.getGameHandler(gameID);
    }
    
}
