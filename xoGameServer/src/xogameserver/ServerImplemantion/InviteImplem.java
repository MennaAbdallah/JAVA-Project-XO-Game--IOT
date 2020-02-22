/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.ServerImplemantion;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Vector;
import xogameserver.DBManagment;
import xogameserver.XoGameServer;
import xogameserver.interfaces.ClientIF;
import xogameserver.interfaces.Invitation;

/**
 *
 * @author nesreen
 */
public class InviteImplem extends UnicastRemoteObject implements Invitation {
    DBManagment dbm = DBManagment.getInstance();
    public InviteImplem() throws RemoteException { }
    
    @Override
    public void invite(int sendId,int rcvId ) throws RemoteException {
        ClientIF rcvObject = XoGameServer.connectedClients.get(rcvId);        
        rcvObject.rcvInvitation(sendId);
        System.out.println(sendId + " sent invitation to "+ rcvId);
    }
    @Override
    public ClientIF changeStatus(int myId) throws RemoteException {
        return XoGameServer.connectedClients.get(myId);
    }

    @Override
    public void accpet(int responceId, int rcvId) throws RemoteException {
        ClientIF rcvObject = XoGameServer.connectedClients.get(rcvId);        
        rcvObject.rcvResponse(responceId,true);
        System.out.println(responceId + " accepted invitation to "+ rcvId);
    }

    @Override
    public void decline(int responceId, int rcvId) throws RemoteException {
        ClientIF rcvObject = XoGameServer.connectedClients.get(rcvId);        
        rcvObject.rcvResponse(responceId,false);
        System.out.println(responceId + " declined invitation to "+ rcvId);    }

    @Override
    public Vector getStatusTable() throws RemoteException {
        System.out.println("getting All Users Status"); 
        return dbm.getAllUsersStatus();
        
    }
}
