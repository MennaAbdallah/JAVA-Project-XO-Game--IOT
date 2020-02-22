/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineMenuScene;

import DTO.SimpleUser;
import LoginScene.Main;
import LoginScene.MusicPlayer;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
<<<<<<< HEAD
import xogameserver.interfaces.Invitation;
=======
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
>>>>>>> origin/ServerOnOff

/**
 *
 * @author menna
 */
public class OnlineMenuController implements Initializable {
<<<<<<< HEAD
    
    private Invitation stub;
    
=======

>>>>>>> origin/ServerOnOff
    @FXML
    private Label label;

    int x = 0;

    @FXML
    private GridPane gPane;

    public ImageView IMute;
    public ImageView INoMute;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Vector<SimpleUser> v = new Vector<>();
        SimpleUser s1 = new SimpleUser("fady", "Jankovic", 7, 100, 1);
        SimpleUser s2 = new SimpleUser("mina", "mon", 8, 200, 1);
        SimpleUser s3 = new SimpleUser("as", "sho3", 9, 900, 0);
           SimpleUser s4 = new SimpleUser("sasdf", "sho3", 9, 900, 1);
              SimpleUser s5 = new SimpleUser("shosada3la", "sho3", 9, 900, 1);
                 SimpleUser s6 = new SimpleUser("sho3sadafla", "sho3", 9, 900, 1);
                    SimpleUser s7 = new SimpleUser("sho3ADASla", "sho3", 9, 900, 1);
                    
        v.add(0, s1);
        v.add(1, s2);
        v.add(2, s3);
        v.add(3, s4);
        v.add(4, s5);
        v.add(5, s6);

        for (x = 0; x < v.size(); x++) {

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
            }
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

    
    
     

}
