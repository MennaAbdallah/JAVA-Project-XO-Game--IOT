/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvationScene;

import DTO.SimpleUser;
import LoginScene.LoginController;
import LoginScene.Main;
import RMI.Rmi;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author fegoo
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    public static SimpleUser sender;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

//      public void InvationReject(ActionEvent actionEvent){
//          Node source =(Node)actionEvent.getSource();
//          Stage theStage = (Stage)source.getScene().getWindow();
//          theStage.close();
//      }
    public void InvationReject(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage theStage = (Stage) source.getScene().getWindow();
        theStage.close();
    }

    @FXML
    public void InvationAccept(ActionEvent actionEvent) throws IOException {
        try {
            Node source = (Node) actionEvent.getSource();
            //stub = Rmi.getInvStub();
            //stub.accpet(LoginController.myData.getId(), sender.getId());
            GameOnline.GameController.setType(-1);
            System.out.println("I accepted " + sender.getId());
            Stage theStage = (Stage) source.getScene().getWindow();
            theStage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/GameOnline/GameScene.fxml"));
            Main.getMyStage().setTitle("Profile");
            Main.getMyStage().setResizable(false);
            Main.getMyStage().setScene(new Scene(root, 909, 509));
            Main.getMyStage().show();
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
