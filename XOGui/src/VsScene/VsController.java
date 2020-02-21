package VsScene;

import LoginScene.LoginController;
import LoginScene.Main;
import RMI.Rmi;
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
    
    private Invitation stub;
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
        catch(Exception IOException){
            System.err.println("Error in Change Scence");
        }
    }
    public void antherPlayer(ActionEvent actionEvent){
        System.out.println("Test");
        sendInvitation();
        changeSceneOnlineList();
            
    }
    public void computerPlay(ActionEvent actionEvent){
        System.out.println("actionEvent");
        changeSceneComputerGame();    
    }
    
    public void sendInvitation(){
        try {
            int myId = LoginController.myData.getId();
            int rcvId = 3;
            stub = Rmi.getInvStub();
            stub.invite(myId, rcvId);
            System.out.println("I sent invitation to "+ rcvId);
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    
    }
}
