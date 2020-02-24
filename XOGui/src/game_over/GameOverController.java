/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_over;

import LoginScene.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 *
 * @author fegoo
 */

  
public class GameOverController implements Initializable {
    
    @FXML
    private Label label;
   
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
    private void newGameAction(ActionEvent event) {
        System.out.println("You clicked me!");
        changeSceneVS();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
