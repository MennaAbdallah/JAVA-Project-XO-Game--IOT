package xogameserver.interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nesreen
 */

public interface DualModeSetupInterface extends Remote {
   //public UnknownObject getInvitation()throws RemoteException;
   //public void Accept(int ID);
   //public void Resume(int ID);
    public Vector getStatusTable() throws RemoteException;
    
    
}
