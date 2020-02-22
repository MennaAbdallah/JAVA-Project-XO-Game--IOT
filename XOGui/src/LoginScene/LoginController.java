package LoginScene;

import DTO.ClientClass;
import xogameserver.interfaces.LoginInterface;
import DTO.SimpleUser;
import InvationScene.FXMLDocumentController;
import static LoginScene.Main.root;
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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//<<<<<<< HEAD
import xogameserver.interfaces.ClientIF;
import xogameserver.interfaces.Invitation;
//=======
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
//>>>>>>> origin/ServerOnOff

/**
 *
 * @author fegoo
 */
public class LoginController implements Initializable {

    public static Stage s1;
    Parent root;
    private LoginInterface stub;
    private Invitation invStub;
    public TextField UserBox;
    public ImageView IMute;
    public ImageView INoMute;
    public PasswordField PasswordBox;
    public Button LoginBtn;
    public Label Massage;

    public static SimpleUser myData;
    public static ClientIF myRef;

//    public void setMyData(SimpleUser myData) {
//        this.myData = myData;
//    }
//
//    public SimpleUser getMyData() {
//        return myData;
//    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Rmi.isConnected() == false) {
            Massage.setText("Failure in Network");
            Massage.setVisible(true);
        }

//<<<<<<< HEAD
        //MediaPlayer me  =MusicPlayer.getMediaplayer();
//        if(MusicPlayer.getMediaplayer().getStatus()!=MediaPlayer.Status.PLAYING){
//            MusicPlayer.getMediaplayer().play();
//        }
    }

//=======
//        if (MusicPlayer.getMediaplayer().getStatus() != MediaPlayer.Status.PLAYING) {
//            if (MusicPlayer.firstTimeCounter == 0) {
//                MusicPlayer.getMediaplayer().play();
//                MusicPlayer.firstTimeCounter++;
//
//            }
//        }
//>>>>>>> origin/ServerOnOff
    public void rcvInviteThread() {
        invStub = Rmi.getInvStub();
        new Thread(() -> {
            while (true) {
                try {
                    myRef = invStub.changeStatus(myData.getId());
                    int senderId = myRef.checkNewInvivtation();
                    if (senderId != 0) {
                        System.out.println(senderId + " sent me invitation");
                        SimpleUser sender = stub.getuserData(senderId);
                        Platform.runLater(() -> {
                            popUpInvitation(sender);
                        });

                    }
                } catch (RemoteException e) {
                    System.err.println("Client exception: " + e.toString());
                }
            }
        }).start();
    }

    public void popUpInvitation(SimpleUser sender) {
        try {
            s1 = new Stage();
            s1.initModality(Modality.WINDOW_MODAL);
            s1.initOwner(Main.myStage);
            root = FXMLLoader.load(getClass().getResource("/InvationScene/FXMLDocument.fxml"));
            FXMLDocumentController.sender = sender;
            Scene scene = new Scene(root);
            Label l = (Label) scene.lookup("#senderName");
            l.setText(sender.getNickName());
            s1.setTitle("TicTacToe");
            s1.initStyle(StageStyle.UNDECORATED);
            s1.setResizable(false);
            s1.setScene(scene);
            s1.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeSceneVS() {
        try {
            rcvInviteThread();
            Parent root = FXMLLoader.load(getClass().getResource("/VsScene/VsScence.fxml"));
            Main.getMyStage().setTitle("TicTacToe");
            Main.getMyStage().setResizable(false);
            Main.getMyStage().setScene(new Scene(root, 909, 509));
            Main.getMyStage().show();
        } catch (Exception IOException) {
            System.err.println("Error in Change Scence");
        }
    }

    public void changeSceneSignUP() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/SignUpScene/Signup.fxml"));
            Scene scene = new Scene(root);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().setResizable(false);
            Main.getMyStage().show();
        } catch (Exception IOException) {
            System.err.println("Error in Change Scence");
        }
    }

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

    public boolean networkLogin(String user_name, String password) {
        boolean login = false;
        try {

            stub = Rmi.getStubLogin();
            int userId = stub.login(user_name, password);
            System.out.println("Remote method invoked");
            if (userId != 0) {
                myData = stub.getuserData(userId);
                stub.setUserOnline(userId);
                System.out.println(userId + " " + myData.getId());
                myRef = new ClientClass();
                System.out.println(myRef.hashCode());
                System.out.println();
                stub.registerClient(myRef, userId);

                Massage.setVisible(false);
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

    public void signUpButton(ActionEvent actionEvent) {

        System.out.println("signUpButton");
        if (Rmi.isConnected() == true) {
            changeSceneSignUP();
        } else {
            Massage.setText("Failure in Network");
            Massage.setVisible(true);
        }

    }

    public void serverStatus(ActionEvent actionEvent) {
        changeSceneServer();
    }

    public void musicControl(ActionEvent actionEvent) {
        MusicPlayer.checkStatus(IMute, INoMute);

    }

    public void TestPopUp(ActionEvent actionEvent) {
        try {
            s1 = new Stage();
            s1.initModality(Modality.WINDOW_MODAL);
            s1.initOwner(Main.myStage);
            root = FXMLLoader.load(getClass().getResource("/InvationScene/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            s1.setTitle("TicTacToe");
            s1.initStyle(StageStyle.UNDECORATED);
            s1.setResizable(false);
            s1.setScene(scene);
            s1.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
