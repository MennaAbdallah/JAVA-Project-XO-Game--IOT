/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.ServerImplemantion;

import DTO.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.DBManagment;
import xogameserver.interfaces.ProfileInterface;

/**
 *
 * @author Mashael
 */
public class ProfileImp  extends UnicastRemoteObject implements ProfileInterface{

    DBManagment dbConnection = DBManagment.getInstance();
    
    public ProfileImp() throws RemoteException{
        
    }
    
    @Override
    public User getUserData(int userID) throws RemoteException {
        return dbConnection.getUser(userID);
    }

    @Override
    public void setNewNickName(int userID, String newNickName) throws RemoteException {
        throw new UnsupportedOperationException("nickname not implementeed yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changePassword(int userID, String oldPassword, String newPassword) throws RemoteException {
        boolean chkPass = dbConnection.checkPassword(userID, oldPassword);
        if(chkPass){
            return dbConnection.setPassword(userID, newPassword);
        }else{
            return false;
        }
    }

    @Override
    public boolean changeEmail(int userID, String newMail) throws RemoteException {
        throw new UnsupportedOperationException("not implementeed yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
