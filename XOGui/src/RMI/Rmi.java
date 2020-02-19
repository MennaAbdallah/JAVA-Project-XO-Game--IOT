/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import xogameserver.interfaces.LoginInterface;
import xogameserver.interfaces.SignUp;

/**
 *
 * @author MinaNagy
 */
public class Rmi {
    private static LoginInterface stub;
    private static SignUp stub2;
    private static String ip="127.0.0.1.2";
    private static int port=50051;
    private static boolean connected=false;
    public static LoginInterface getStubLogin() {
        return stub;
    }
    public static SignUp getStubSignUp() {
        return stub2;
    }
    public static void setStubLogin(LoginInterface s) {
         stub=s;
    }
    public static void setStubSignUp(SignUp s) {
         stub2=s;
    }

    public static String getIp() {
        return ip;
    }

    public static int getPort() {
        return port;
    }

    public static void setIp(String ip) {
        Rmi.ip = ip;
    }

    public static void setPort(int port) {
        Rmi.port = port;
    }

    public static void setConnected(boolean connected) {
        Rmi.connected = connected;
    }

    public static boolean isConnected() {
        return connected;
    }
    public static boolean connectedSevrver(){
                try {
                /*System.setProperty("java.security.policy","file:E:/security.policy");
                if (System.getSecurityManager() == null)
                {
                    System.setSecurityManager(new SecurityManager());
                }*/
                // Getting the registry
                //Rmi.setIp("127.0.0.1");
                //Rmi.setPort(5005);
                 Registry registry = LocateRegistry.getRegistry(Rmi.getIp(),Rmi.getPort());
                 //Registry registry = LocateRegistry.getRegistry("192.168.1.5",5030);
                 // Looking up the registry for the remote object
                 setStubLogin((LoginInterface) registry.lookup("login"));
                 setStubSignUp((SignUp) registry.lookup("signup"));
                // Calling the remote method using the obtained object
                 connected=true;
            }
            catch (Exception e) {
                connected=false;
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        return connected;
    }
    
}
