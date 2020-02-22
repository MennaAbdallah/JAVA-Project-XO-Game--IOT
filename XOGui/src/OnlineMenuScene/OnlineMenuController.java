/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineMenuScene;

import DTO.SimpleUser;
import LoginScene.LoginController;
import LoginScene.Main;
import LoginScene.MusicPlayer;
import RMI.Rmi;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//<<<<<<< HEAD
import xogameserver.interfaces.Invitation;
//=======
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
//>>>>>>> origin/ServerOnOff

/**
 *
 * @author menna
 */
public class OnlineMenuController implements Initializable {
//<<<<<<< HEAD

    private Invitation stub;

//=======
//>>>>>>> origin/ServerOnOff
    @FXML
    private Label label;

    int x = 0;

    @FXML
    private GridPane gPane;

    public ImageView IMute;
    public ImageView INoMute;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            stub = Rmi.getInvStub();
            Vector<SimpleUser> v = stub.getStatusTable();
            for (x = 0; x < v.size()-1; x++) {

                Label lUserName = new Label();
                lUserName.setId("lUserName" + x);

                lUserName.setText(v.get(x).getNickName());
                gPane.add(lUserName, 0, x);

                Label lScore = new Label();
                lScore.setId("lScore" + x);
                lScore.setText("" + v.get(x).getScore());
                gPane.add(lScore, 1, x);

                if (v.get(x).getStatus() == 1) {
                    Button lInvitaion = new Button();
                    lInvitaion.setText("Online");
                    gPane.add(lInvitaion, 2, x);
                    
                    lInvitaion.setOnAction(event -> {
                        System.out.println(v.get(x).getId());
                        sendInvitation(v.get(x).getId());
                    });
                }
            }
        } catch (RemoteException ex) {
            System.err.println("Client exception: " + ex.toString());
            ex.printStackTrace();
        }

    }

    public void musicControl(ActionEvent actionEvent) {
        MusicPlayer.checkStatus(IMute, INoMute);

    }

    public void GoBack(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/LoginScene/login.fxml"));
            Scene scene = new Scene(root, 909, 509);
            Main.getMyStage().setTitle("TicTacToe");
            Main.getMyStage().setResizable(false);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().show();
        } catch (Exception IOException) {
            System.err.println("Error in Change Scence");
        }

    }

    public void sendInvitation(int rcvId) {
        try {
            int myId = LoginController.myData.getId();
            stub.invite(myId, rcvId);
            System.out.println("I sent invitation to " + rcvId);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }

}
