/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.interfaces;

import DTO.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Mashael
 */
public interface ProfileInterface extends Remote {
    User getUserData(int userID)throws RemoteException;
    void setNewNickName(int userID,String newNickName) throws RemoteException;
    boolean changePassword(int userID,String oldPassword,String newPassword) throws RemoteException;
    boolean changeEmail(int userID,String newMail) throws RemoteException;

    
}
