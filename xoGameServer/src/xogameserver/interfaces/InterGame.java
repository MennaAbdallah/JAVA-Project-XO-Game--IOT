
import java.rmi.Remote;
import java.rmi.RemoteException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nesreen
 */

public interface InterGame extends Remote {
   public boolean setGameType(int ID) throws RemoteException;
   public boolean serverConnected()throws RemoteException;
   //public UnknownObject getInvitation()throws RemoteException;
   public void Accept(int ID);
   public void Resume(int ID);
} 