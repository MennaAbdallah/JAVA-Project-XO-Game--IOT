/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import xogameserver.interfaces.ClientIF;
//import xogameserver.InvitationRcv;

/**
 *
 * @author nesreen
 */
public class ClientClass extends UnicastRemoteObject implements Serializable, ClientIF {

    private HashMap<Integer, String> requests;
    private HashMap<Integer, String> responces;

    public ClientClass() throws RemoteException {
        requests = new HashMap<>();
        responces = new HashMap<>();
    }
    
    @Override
    public void rcvInvitation(int sendId) throws RemoteException {
        requests.put(sendId, "invite");
    }

    @Override
    public void rcvResponse(int respondId, boolean accepted) throws RemoteException {
        if (accepted) {
            requests.put(respondId, "accepted");
        } else {
            requests.put(respondId, "declined");
        }
    }
    
        @Override
    public int checkNewInvivtation() throws RemoteException{
        for (Map.Entry req : requests.entrySet()) {
            if (req.getValue() == "invite") {
                req.setValue("seen");
                return (int) req.getKey();
            }
        }
        return 0;
    }
    @Override
    public int checkNewResponce() throws RemoteException{
        Iterator iterator = responces.entrySet().iterator();
        do {
            Map.Entry req = (Map.Entry) iterator.next();
            if (req.getValue() == "accepted") {
                responces.remove(req.getKey());
                return (int) req.getKey();
            } else if (req.getValue() == "declined") {
                responces.remove(req.getKey());
                return -(int) req.getKey();
            }

        } while (iterator.hasNext());
        return 0; 
    }


}
