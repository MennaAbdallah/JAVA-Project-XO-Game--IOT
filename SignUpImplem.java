/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.ServerImplemantion;

import java.rmi.RemoteException;
import DTO.User;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.DBManagment;
import xogameserver.interfaces.SignUp;

/**
 *
 * @author fegoo
 */
public class SignUpImplem extends UnicastRemoteObject implements SignUp{
    
    
    
    DBManagment db =DBManagment.getInstance();
    User signUser = new User();
    boolean isSignned  ;
    
   public SignUpImplem()throws RemoteException{}

    @Override
    public boolean signUp(User user) throws RemoteException {
        System.out.println(user.toString());
        isSignned = db.insertNewUser(user);
        return isSignned ;
       
    }
    
    
    
    
    
}
