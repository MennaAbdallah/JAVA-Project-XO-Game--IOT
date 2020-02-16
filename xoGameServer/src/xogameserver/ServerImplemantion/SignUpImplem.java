/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.ServerImplemantion;

import java.rmi.RemoteException;
import DTO.User;
import xogameserver.DBManagment;
import xogameserver.interfaces.SignUp;

/**
 *
 * @author fegoo
 */
public class SignUpImplem implements SignUp{
    
    
    DBManagment test =new DBManagment();
    User koko = new User();
    boolean value ;

    @Override
    public boolean signUp(User user) throws RemoteException {
        value = test.insertNewUser(koko);
        return value ;
       
    }
    
    
    
    
    
}
