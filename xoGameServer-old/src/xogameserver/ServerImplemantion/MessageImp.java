/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.ServerImplemantion;
import DTO.GameOnlineClass.MessagePayload;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.DBManagment;
import DTO.GameOnlineClass.MessagePayload;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.interfaces.Message;

/**
 *
 * @author MinaNagy
 */
public class MessageImp extends UnicastRemoteObject implements Message {
   
   private MessagePayload messagePayload = new MessagePayload();
   public MessageImp() throws RemoteException {

    }
   public void sendMessage(MessagePayload messagePayload) throws RemoteException {
        this.messagePayload=messagePayload;
    }

    public MessagePayload getMessage() throws RemoteException {
        return messagePayload;
    }
    
    public void resetMessage() throws RemoteException {
        messagePayload=null;
    }
}