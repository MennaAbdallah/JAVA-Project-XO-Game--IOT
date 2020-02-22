/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author nesreen
 */
public interface Invitation extends Remote{
    public ClientIF changeStatus(int myId) throws RemoteException ;
    public void invite(int sendId, int rcvId)throws RemoteException;
    public void accpet(int responceId, int rcvId)throws RemoteException;
    public void decline(int responceId, int rcvId)throws RemoteException;
    public Vector getStatusTable() throws RemoteException;
}

