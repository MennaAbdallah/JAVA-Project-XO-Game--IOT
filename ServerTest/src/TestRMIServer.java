import xogameserver.interfaces.LoginInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import xogameserver.interfaces.SignUp;
import xogameserver.interfaces.gameInterface;

public class TestRMIServer 
{
    public static void main(String[] args) {
        ImplExample obj = new ImplExample();
        ImpSignUp s=new ImpSignUp();
            try
            {
                LoginInterface sub = (LoginInterface) UnicastRemoteObject.exportObject((Remote) obj,5005);
                SignUp sub2 = (SignUp) UnicastRemoteObject.exportObject((Remote) s,5005);
            //   gameInterface subgame= (gameInterface) UnicastRemoteObject.exportObject((Remote) s,5005);
                Registry registry = LocateRegistry.createRegistry(5005);
                registry.bind("login",sub);
                registry.bind("signup",sub2);
               // registry.bind("game",(Remote)subgame);
                System.out.println("Server Ready");
            } 
            catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace();
            }

    }
}
