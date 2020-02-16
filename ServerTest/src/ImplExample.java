import DTO.SimpleUser;
import xogameserver.interfaces.LoginInterface;
import java.rmi.RemoteException;
public class ImplExample implements LoginInterface {

    @Override
    public boolean login(String username, String pass) throws RemoteException {
        System.out.println("User Name : "+username);
        System.out.println("Password : "+pass);
        return false;
    }
    @Override
    public SimpleUser getuserData() {
        System.out.println("Get User Data");
            return new SimpleUser();
    }
}
