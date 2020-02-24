/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.ServerImplemantion;

import java.rmi.RemoteException;
import DTO.SimpleUser;
import DTO.User;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.DBManagment;
import xogameserver.interfaces.LoginInterface;

/**
 *
 * @author fegoo
 */
public class LoginImplem extends UnicastRemoteObject implements LoginInterface {

    DBManagment db = DBManagment.getInstance();
    User loginUser = new User();

    int loginId;

    public LoginImplem() throws RemoteException {
    }

    @Override
    public int login(String username, String pass) throws RemoteException {

        loginUser.setUserName(username);
        loginUser.setPassword(pass);
        System.out.println(loginUser.getUserName());
        loginId = db.logIn(loginUser);
        db.setStatus(loginId, 1);

        if (loginId > 0) {
            return loginId;
        } else {
            return -1;
        }

    }

    @Override
    public SimpleUser getuserData(int userID) throws RemoteException {
        loginUser = db.getUser(loginId);
        return loginUser;
    }

    @Override
    public void setUserLogoutStatus(int userID) throws RemoteException {
        db.setStatus(userID, 0);
    }

}
