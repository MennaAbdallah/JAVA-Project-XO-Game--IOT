package LoginScene;

import xogameserver.interfaces.LoginInterface;
import DTO.SimpleUser;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private LoginInterface stub;
    public TextField UserBox;
    public PasswordField PasswordBox;
    public Button LoginBtn;
    public Label Massage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public boolean networkLogin(String user_name,String password){
        boolean login=false; 
        try {
             // Getting the registry
            //Registry registry = LocateRegistry.getRegistry("127.0.0.1",5005);
            // Looking up the registry for the remote object
            //LoginInterface stub = (LoginInterface) registry.lookup("Hello");
            // Calling the remote method using the obtained object
            stub=Main.getStub();
            login=stub.login(user_name,password);
             System.out.println("Remote method invoked");
            if(login==true){
                SimpleUser s=stub.getuserData();
                Massage.setVisible(false);
            }
            else{
                Massage.setText("User Name or Passaword incorrect");
                Massage.setVisible(true);
            
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return login;
    }


    public void test(ActionEvent actionEvent) {

      if (PasswordBox.getText().equals("") || UserBox.getText().equals("")) {
            Massage.setText("Check Your Fields");
            Massage.setVisible(true);
        }

      else if (PasswordBox.getLength() <8)
      { System.out.println(PasswordBox.getLength());
          Massage.setText("Your password is less than 8 ");
          Massage.setVisible(true);
      }
      else {
            networkLogin(UserBox.getText(), PasswordBox.getText());
        }


    }
}
