package VsScene;

import LoginScene.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author fegoo
 */
public class VsController {
    public void changeSceneComputerGame() {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/GameScene/GameScene.fxml"));
        Main.getMyStage().setTitle("TicTacToe");
        Main.getMyStage().setResizable(false);
        Main.getMyStage().setScene(new Scene(root, 909, 509));
        Main.getMyStage().show();
        }
        catch(Exception IOException){
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
    public void antherPlayer(ActionEvent actionEvent){
        System.out.println("Test");
        changeSceneOnlineList();
            
    }
    public void computerPlay(ActionEvent actionEvent){
        System.out.println("actionEvent");
        changeSceneComputerGame();    
    }
}
