/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.interfaces;

import DTO.GameOnlineClass.MessagePayload;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author MinaNagy
 */
public interface Message extends Remote {
    void sendMessage(MessagePayload messagePayload) throws RemoteException;
    MessagePayload getMessage() throws RemoteException;
    void resetMessage()throws RemoteException;
}
