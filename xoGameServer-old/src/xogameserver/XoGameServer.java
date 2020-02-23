/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameserver;

import DTO.GameOnlineClass.GameHandler;
import DTO.GameOnlineClass.GameHandlersList;
import xogameserver.ServerImplemantion.GameImp;
import java.net.InetAddress;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.ServerImplemantion.LoginImplem;
import xogameserver.ServerImplemantion.MessageImp;
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
        
        
        System.setProperty("java.rmi.server.hostname", "192.168.1.26");

        System.setProperty("java.security.policy","file:./src/xogameserver/security.policy");
        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }
        
        
         try { 
             GameHandler gameHandler= new GameHandler();
             gameHandler.setGameID(1);
             gameHandler.setFirstPlayerID(1);
             gameHandler.setSecondPlayerID(2);
             GameHandlersList.addGameHandler(gameHandler);
             
         // Instantiating the implementation class 
         LoginImplem loginStub = new LoginImplem(); 
         SignUpImplem signStub = new SignUpImplem();
         GameImp gameStub = new GameImp();
         MessageImp messageStub=new MessageImp();
   
         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.createRegistry(5100); 
         
         registry.bind("login", loginStub);
         registry.bind("signup", signStub);
         registry.bind("game", gameStub);
         registry.bind("message", messageStub);
         System.err.println("Server ready"); 
      } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
    }
    
}
