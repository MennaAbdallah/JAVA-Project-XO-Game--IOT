/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerImplementation;

import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import xo_game.DBManagment;
import xo_game.SimpleUser;
import xogameserver.interfaces.DualModeSetupInterface;

/**
 *
 * @author nesreen
 */
public class DualModeSetupImp implements DualModeSetupInterface{
    //DBManagment.connect();
    @Override
    public Vector getStatusTable() {
        try {
            DBManagment dbm =   DBManagment.getInstance();
            return dbm.getAllUsersStatus();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new NullPointerException("Can't connect to db");
        }
    }
}
