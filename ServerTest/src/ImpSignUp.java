import DTO.SimpleUser;
import DTO.User;
import xogameserver.interfaces.LoginInterface;
import java.rmi.RemoteException;
import xogameserver.interfaces.SignUp;
public class ImpSignUp implements SignUp {

    @Override
    public boolean signUp(User user) throws RemoteException {
            System.out.println("Sign Up Run ");
            return true;
    }


}
