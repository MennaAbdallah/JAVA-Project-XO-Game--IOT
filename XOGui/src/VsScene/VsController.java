package VsScene;

import LoginScene.LoginController;
import LoginScene.Main;
import RMI.Rmi;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import xogameserver.interfaces.Invitation;


/**
 *
 * @author fegoo
 */
public class VsController {
    
    public void changeSceneComputerGame() {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/gamescene_1/FXMLDocument.fxml"));
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
        catch(IOException IOException){
            System.err.println("Client exception: " + IOException.toString());
            IOException.printStackTrace();
//            System.err.println("Error in Change Scence");
        }
    }
    public void antherPlayer(ActionEvent actionEvent){
        System.out.println("Test");
        changeSceneOnlineList();
            
    }
    public void computerPlay(ActionEvent actionEvent){
        System.out.println("actionEvent");
        changeSceneComputerGame();    
    }
    

}
