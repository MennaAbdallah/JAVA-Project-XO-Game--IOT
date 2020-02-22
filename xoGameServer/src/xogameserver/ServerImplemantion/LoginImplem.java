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
    public int login(String username, String pass) throws RemoteException {

        loginUser.setUserName(username);
        loginUser.setPassword(pass);
        System.out.println(db.logIn(loginUser));
        return db.logIn(loginUser);
        
    }

    @Override
    public SimpleUser getuserData(int loginId) throws RemoteException {

        loginUser = db.getUser(loginId);

        return loginUser;

    }

    @Override
    public void registerClient(ClientIF clientRef, int loginId) throws RemoteException{
        System.out.println(clientRef.hashCode());
        XoGameServer.connectedClients.putIfAbsent(loginId, clientRef);
//        XoGameServer.connectedClients.put(loginId, clientRef);
        System.out.println("new refrence is added to server"); 
    }
    
    @Override
    public void setUserOnline(int loginId) throws RemoteException{
        db.setStatus(loginId,1);
    }
}
