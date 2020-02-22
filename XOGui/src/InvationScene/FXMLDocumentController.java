/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvationScene;

import DTO.SimpleUser;
import LoginScene.LoginController;
import RMI.Rmi;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import xogameserver.interfaces.Invitation;

/**
 *
 * @author fegoo
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    public static SimpleUser sender;
    private Invitation stub ;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void InvationReject(ActionEvent actionEvent){
          Node source =(Node)actionEvent.getSource();
          Stage theStage = (Stage)source.getScene().getWindow();
          theStage.close();
    }
    
    public void InvationAccept(ActionEvent actionEvent){
        try {
            Node source =(Node)actionEvent.getSource();
            stub = Rmi.getInvStub();
            stub.accpet(LoginController.myData.getId(),sender.getId());
            System.out.println("I accepted " + sender.getId());
            Stage theStage = (Stage)source.getScene().getWindow();
            theStage.close();
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
