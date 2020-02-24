/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.interfaces;

import DTO.GameOnlineClass.GameHandler;
import DTO.InvitationPayload;
import DTO.SimpleUser;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Mashael
 */
public interface InvitationInterface extends Remote{
    Vector<SimpleUser> getUsers()throws RemoteException;
    int sendInvitation(int senderID,String senderName,int recID)throws RemoteException;
    void acceptInvitation(int gameID,int recID)throws RemoteException;
    void refuseInvitation(int gameID,int recID)throws RemoteException;
    InvitationPayload getNewInvitation()throws RemoteException;
    GameHandler getMyInvitationState(int gameID)throws RemoteException;

}
