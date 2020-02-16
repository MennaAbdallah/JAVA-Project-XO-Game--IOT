package LoginScene;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField UserBox;
    public PasswordField PasswordBox;
    public Button LoginBtn;
    public Label Massage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            Massage.setVisible(false);
        }

    }



}
