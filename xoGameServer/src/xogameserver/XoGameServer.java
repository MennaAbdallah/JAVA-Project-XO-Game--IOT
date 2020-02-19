/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver;

import java.net.InetAddress;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.ServerImplemantion.LoginImplem;
import xogameserver.ServerImplemantion.SignUpImplem;
import xogameserver.interfaces.LoginInterface;

/**
 *
 * @author fegoo
/**
 *
 * @author mashael
 */
public class XoGameServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.net.UnknownHostException {
        
        
        System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());

        System.setProperty("java.security.policy","file:C:/Users/fegoo/Desktop/JAVA-Project-XO-Game--IOT/xoGameServer/src/xogameserver/security.policy");
        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }
        
        
         try { 
         // Instantiating the implementation class 
         LoginImplem loginStub = new LoginImplem(); 
         SignUpImplem signStub = new SignUpImplem();
                 
         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.createRegistry(5030); 
         
         registry.bind("login", loginStub);
         registry.bind("signup", signStub);
         System.err.println("Server ready"); 
      } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
    }
    
}
