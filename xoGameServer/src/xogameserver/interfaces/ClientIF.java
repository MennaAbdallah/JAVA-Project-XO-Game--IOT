/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author nesreen
 */
public interface ClientIF extends Remote {

    public void rcvInvitation(int sendId) throws RemoteException;

    public void rcvResponse(int respondId, boolean type) throws RemoteException;

    public int checkNewInvivtation() throws RemoteException;

    public int checkNewResponce() throws RemoteException;

}
