package xogameserver.interfaces;
/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
*/
import DTO.SimpleUser;
import java.rmi.Remote;
import java.rmi.RemoteException;
/* * * @author MinaNagy */
public interface LoginInterface extends Remote 
{
    public int login(String username,String pass)throws RemoteException;
    public SimpleUser getuserData(int loginId) throws RemoteException;
    public void registerClient(ClientIF clientRef, int loginId)throws RemoteException;
    public void setUserOnline(int loginId) throws RemoteException;

}
