/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerInterfaceScene;

import DTO.SimpleUser;
import LoginScene.Main;
import LoginScene.MusicPlayer;
import RMI.Rmi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.regex.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

/**
 *
 * @author menna
 */
public class ServerOnlineListController implements Initializable {

    @FXML
    private Label serverIPErr, serverPortErr, DBHostErr, DBPortErr;

    @FXML
    private TextField serverIPTF, serverPortTF, DBHostTF, DBPortTF;

    public ImageView IMute;
    public ImageView INoMute;

    public void changeSceneLogin() {
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

    @FXML

    public void GoBack(ActionEvent actionEvent) {
        try {
            System.out.println("Mina");
            Parent root = FXMLLoader.load(getClass().getResource("/LoginScene/login.fxml"));
            Scene scene = new Scene(root, 909, 509);
            Main.getMyStage().setTitle("TicTacToe");
            Main.getMyStage().setResizable(false);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().show();
        } catch (IOException ex) {
            Logger.getLogger(ServerOnlineListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void connectClick(MouseEvent event) {
        System.out.println("Click on Connected");
        if (!serverIPTF.getText().matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")) {
            serverIPErr.setText("Invalid IP");
        } else {
            serverIPErr.setText("");
            Rmi.setIp(serverIPTF.getText());
            Rmi.setPort(Integer.parseInt(serverPortTF.getText()));
            Rmi.connectedSevrver();
            changeSceneLogin();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void musicControl(ActionEvent actionEvent) {
        MusicPlayer.checkStatus(IMute, INoMute);

    }

}

