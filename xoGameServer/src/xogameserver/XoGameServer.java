/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.ServerImplemantion.LoginImplem;
import xogameserver.interfaces.LoginInterface;

/**
 *
 * @author mashael
 */
public class XoGameServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
         try { 
         // Instantiating the implementation class 
         LoginImplem obj = new LoginImplem(); 
    
         // Exporting the object of implementation class  
         // (here we are exporting the remote object to the stub) 
         LoginInterface stub = (LoginInterface) UnicastRemoteObject.exportObject(obj, 5030);  
         
         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.createRegistry(5030); 
         
         registry.bind("Hello", stub);  
         System.err.println("Server ready"); 
      } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
    }
    
}
