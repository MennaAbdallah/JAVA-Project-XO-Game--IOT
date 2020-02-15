
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
public interface SignUp extends Remote {
    public boolean signUp(User user) throws RemoteException;
    public boolean serverConnected()throws RemoteException;
}


