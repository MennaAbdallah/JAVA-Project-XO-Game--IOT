package xogameserver.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import xo_game.SimpleUser;

/**
 *
 * @author MinaNagy
 */
public interface LoginInterface extends Remote {
    public boolean login(String username,String pass)throws RemoteException;
    public int getuserID()throws RemoteException;
    public SimpleUser getuserData(int id);
}
