/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Vector;
import xo_game.DBManagment;
import xogameserver.interfaces.DualModeSetupInterface;

/**
 *
 * @author nesreen
 */
public class DualModeSetupImp extends UnicastRemoteObject implements DualModeSetupInterface{
    //DBManagment.connect();
    
    
    public DualModeSetupImp() throws RemoteException { 
        super(); 
    }

    @Override
    public Vector getStatusTable() throws RemoteException {
        try {
            DBManagment dbm =   DBManagment.getInstance();
            return dbm.getAllUsersStatus();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new NullPointerException("Can't connect to db");
        }
    }
}
