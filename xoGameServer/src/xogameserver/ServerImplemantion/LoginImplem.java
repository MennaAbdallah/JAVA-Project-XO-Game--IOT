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
import xogameserver.XoGameServer;
import xogameserver.interfaces.ClientIF;
import xogameserver.interfaces.LoginInterface;

/**
 *
 * @author fegoo
 */
public class LoginImplem extends UnicastRemoteObject implements LoginInterface {

    DBManagment db = DBManagment.getInstance();
    User loginUser = new User();

    int loginId;

    public LoginImplem() throws RemoteException { }

    @Override
    public boolean login(String username, String pass) throws RemoteException {

        loginUser.setUserName(username);
        loginUser.setPassword(pass);

        loginId = db.logIn(loginUser);
        
        if (loginId > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SimpleUser getuserData() throws RemoteException {

        loginUser = db.getUser(loginId);

        return loginUser;

    }

    @Override
    public void registerClient(ClientIF clientRef) throws RemoteException{
        System.out.println(clientRef.hashCode());
        XoGameServer.connectedClients.put(loginId, clientRef);
        System.out.println("new refrence is added to server"); 
    }
}
