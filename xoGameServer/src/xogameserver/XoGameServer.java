/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver;

import ServerImplementation.DualModeSetupImp;
import java.rmi.AlreadyBoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.interfaces.DualModeSetupInterface;

/**
 *
 * @author mashael
 */
public class XoGameServer extends DualModeSetupImp {

    public XoGameServer() throws RemoteException{
    }

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            DualModeSetupImp dm = new DualModeSetupImp();
            DualModeSetupInterface stub = (DualModeSetupInterface)
                    UnicastRemoteObject.exportObject(dm, 0);

            Registry registry = LocateRegistry.getRegistry();

            registry.bind("test", stub);
            System.err.println("Server ready");
//            DBManagment dbm = DBManagment.getInstance();
//            Vector<SimpleUser> test = dbm.getAllUsersStatus();
//            System.out.println(test.firstElement().getUserName());
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(XoGameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
