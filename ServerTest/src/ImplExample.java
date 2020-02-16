import RMI_Interfaces.LoginInterface;
import java.rmi.RemoteException;
public class ImplExample implements LoginInterface {

    @Override
    public boolean login(String username, String pass) throws RemoteException {
        System.out.println("User Name : "+username);
        System.out.println("Password : "+pass);
        return true;
    }

    @Override
    public int getuserID() throws RemoteException 
    {
        return 50;
    }

    //@Override
    /*public SimpleUser getuserData(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
