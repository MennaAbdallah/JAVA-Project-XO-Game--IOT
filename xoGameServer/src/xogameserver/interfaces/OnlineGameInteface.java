package xogameserver.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author MinaNagy
 */
public interface OnlineGameInteface extends Remote{
    public boolean serverConnected()throws RemoteException;
    //public UnknowObject getOnlinePlay()throws RemoteException;
    public boolean invite(int id)throws RemoteException;
    
}
