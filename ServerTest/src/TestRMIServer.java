import xogameserver.interfaces.LoginInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TestRMIServer 
{
    public static void main(String[] args) {
        ImplExample obj = new ImplExample();
            try
            {
                LoginInterface sub = (LoginInterface) UnicastRemoteObject.exportObject((Remote) obj,5005);
                Registry registry = LocateRegistry.createRegistry(5005);
                registry.bind("Hello",sub);
                System.out.println("Server Ready");
            } 
            catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace();
            }

    }
}
