/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver;

import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import xo_game.DBManagment;
import xo_game.SimpleUser;

/**
 *
 * @author mashael
 */
public class XoGameServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            DBManagment dbm = DBManagment.getInstance();
            //System.out.println("DB connected successfully");
            Vector<SimpleUser> test = dbm.getAllUsersStatus();
            
            System.out.println(test.firstElement().getUserName());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
