/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver.ServerImplemantion;

import java.rmi.RemoteException;
import DTO.SimpleUser;
import DTO.User;
import xogameserver.DBManagment;
import xogameserver.interfaces.LoginInterface;




/**
 *
 * @author fegoo
 */
public class LoginImplem implements LoginInterface{

               DBManagment test =new DBManagment();
        User koko = new User();
        
        int login ;

    
    @Override
    public boolean login(String username, String pass) throws RemoteException {

         koko.setUserName(username);
         koko.setPassword(pass);
        System.out.println(koko.getUserName());
        login = test.logIn(koko);
                
                    if (login > 0)
                    {
                        return true ;
                    }
                    else 
                    {
                        return false ;
                    }
                    

    }

    @Override
    public SimpleUser getuserData() {
        
      koko= test.getUser(login);
      
      return koko;
      
        
       
    }

 
    
    
}
