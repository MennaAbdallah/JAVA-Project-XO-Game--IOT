package VsScene;

import LoginScene.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author fegoo
 */
public class VsController {
    public void changeSceneComputerGame() {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/gamescene_ai/GameAi.fxml"));
        Main.getMyStage().setTitle("TicTacToe");
        Main.getMyStage().setResizable(false);
        Main.getMyStage().setScene(new Scene(root, 909, 509));
        Main.getMyStage().show();
        }
        catch(Exception IOException){
            IOException.printStackTrace();
            System.err.println("Error in Change Scence");
        }
    }
    public void changeSceneOnlineList() {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/OnlineMenuScene/OnlineMenu.fxml"));
        Main.getMyStage().setTitle("TicTacToe");
        Main.getMyStage().setResizable(false);
        Main.getMyStage().setScene(new Scene(root, 909, 509));
        Main.getMyStage().show();
        }
        catch(Exception IOException){
            System.err.println("Error in Change Scence");
        }
    }
        public void changeSceneGameOnline() {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/GameOnline/GameScene.fxml"));
        Main.getMyStage().setTitle("TicTacToe");
        Main.getMyStage().setResizable(false);
        Main.getMyStage().setScene(new Scene(root, 909, 509));
        Main.getMyStage().show();
     
        }
        catch(Exception IOException){
            System.err.println("Error in Change Scence");
        }
    }
             @FXML
    public void antherPlayer(ActionEvent actionEvent){
        System.out.println("Test");
        //changeSceneOnlineList();
        changeSceneOnlineList();
        //changeSceneGameOnline();
            
    }
    
         @FXML
    public void computerPlay(ActionEvent actionEvent){
        System.out.println("actionEvent");
       // changeSceneComputerGame();
       changeSceneComputerGame();
       

    }
    
     @FXML
    public void profileOnAction(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ProfileScene/FXMLDocument.fxml"));
            Main.getMyStage().setTitle("Profile");
            Main.getMyStage().setResizable(false);
            Main.getMyStage().setScene(new Scene(root, 909, 509));
            Main.getMyStage().show();
        } catch (IOException ex) {
            Logger.getLogger(VsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
