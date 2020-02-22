package LoginScene;

import xogameserver.interfaces.LoginInterface;
import DTO.SimpleUser;
import RMI.Rmi;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author fegoo
 */
public class LoginController implements Initializable {

    private LoginInterface stub;
    @FXML
    public TextField UserBox;

    @FXML
    public ImageView IMute;
    @FXML
    public ImageView INoMute;
    @FXML
    public PasswordField PasswordBox;
    @FXML
    public Button LoginBtn;
    @FXML
    public Label Massage;
    @FXML
    private Circle status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rmi.connectedSevrver();
        if (Rmi.isConnected() == false) {
            Massage.setText("Failure in Network");
            Massage.setVisible(true);
            status.setFill(Paint.valueOf("#808080"));//paint
            //status.setStyle(null);//String
        }
        MediaPlayer me = MusicPlayer.getMediaplayer();

        if (MusicPlayer.getMediaplayer().getStatus() != MediaPlayer.Status.PLAYING) {
            MusicPlayer.getMediaplayer().play();
        }

    }

    @FXML
    public void changeSceneVS() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/VsScene/VsScence.fxml"));
            Main.getMyStage().setTitle("TicTacToe");
            Main.getMyStage().setResizable(false);
            Main.getMyStage().setScene(new Scene(root, 909, 509));
            Main.getMyStage().show();
        } catch (Exception IOException) {
            System.err.println("Error in Change Scence");
        }
    }

    @FXML
    public void changeSceneSignUP() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/signupscene/Signup.fxml"));
            Scene scene = new Scene(root);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().setResizable(false);
            Main.getMyStage().show();
        } catch (Exception IOException) {
            System.err.println("Error in Change Scence");
        }
    }

    @FXML
    public void changeSceneServer() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ServerInterfaceScene/ServerInterfaceScene.fxml"));
            Scene scene = new Scene(root);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().setResizable(false);
            Main.getMyStage().show();
        } catch (Exception IOException) {
            System.err.println("Error in Change Scence");
        }
    }

    @FXML
    public boolean networkLogin(String user_name, String password) {
        boolean login = false;
        try {
            // Getting the registry
            //Registry registry = LocateRegistry.getRegistry("127.0.0.1",5005);
            // Looking up the registry for the remote object
            //LoginInterface stub = (LoginInterface) registry.lookup("Hello");
            // Calling the remote method using the obtained object

            stub = Rmi.getStubLogin();
            login = stub.login(user_name, password);
            System.out.println("Remote method invoked");
            if (login == true) {
                SimpleUser s = stub.getuserData();
                Massage.setVisible(false);
                //changeSceneVS();
                changeSceneVS();
            } else {
                Massage.setText("User Name or Passaword incorrect");
                Massage.setVisible(true);

            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        return login;
    }

    @FXML
    public void test(ActionEvent actionEvent) {
        if (Rmi.isConnected() == true) {
            if (PasswordBox.getText().equals("") || UserBox.getText().equals("")) {
                Massage.setText("Check Your Fields");
                Massage.setVisible(true);
            } else if (PasswordBox.getLength() < 8) {
                System.out.println(PasswordBox.getLength());
                Massage.setText("Your password is less than 8 ");
                Massage.setVisible(true);
            } else {
                networkLogin(UserBox.getText(), PasswordBox.getText());
            }
        } else {
            Massage.setText("Failure in Network");
            Massage.setVisible(true);
        }
    }

    @FXML
    public void signUpButton(ActionEvent actionEvent) {

        System.out.println("signUpButton");
        if (Rmi.isConnected() == true) {
            changeSceneSignUP();
        } else {
            Massage.setText("Failure in Network");
            Massage.setVisible(true);
        }

    }

    @FXML
    public void serverStatus(ActionEvent actionEvent) {
        changeSceneServer();
    }

    @FXML
    public void musicControl(ActionEvent actionEvent) {
        MusicPlayer.checkStatus(IMute, INoMute);

    }

}
