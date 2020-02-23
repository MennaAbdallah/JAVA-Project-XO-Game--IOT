/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfileScene;

import LoginScene.Main;
import RMI.Rmi;
import ServerInterfaceScene.ServerOnlineListController;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import xogameserver.interfaces.ProfileInterface;
/**
 *
 * @author menna
 */
public class FXMLDocumentController implements Initializable {
    
    ProfileInterface profileStub ;
    @FXML
    public VBox passwdVbox ,emailVbox;
    
    @FXML
    private PasswordField oldPWTF,newPWTF,confirmPWTF;
            
    @FXML
    private TextField oldEmailTF,newEmailTF,confirmEmailTF;
    
    @FXML
    Label currentScore,nickNameLabel;
            
    @FXML
    private Label pwErr,emailErr;
    
 
    @FXML
    public void passwdClick(MouseEvent event) {
        passwdVbox.setVisible(true);
    }
    
    
    @FXML
    public void savePWClick(MouseEvent event) {
        if(oldPWTF.getText().trim().isEmpty()||newPWTF.getText().trim().isEmpty()||confirmPWTF.getText().trim().isEmpty()) {
            pwErr.setText("Please enter your password!");
            passwdVbox.setVisible(true);
        }
        else if(!newPWTF.getText().matches("(\\w{8,})")){
            pwErr.setText("Invalid Password");
            passwdVbox.setVisible(true);
        }
        else if(!oldPWTF.getText().equals(newPWTF.getText())){
            pwErr.setText("Not matched Password");
            passwdVbox.setVisible(true);
        }
        else
        {
            pwErr.setText("");
            passwdVbox.setVisible(false);
        }
    }       
    
    
       
        
    
    @FXML
    public void emailClick(MouseEvent event) {
        
        emailVbox.setVisible(true);
    }
    
      @FXML
    public void onBackClick(ActionEvent event) {
        
          try {
            System.out.println("Mina");
            Parent root = FXMLLoader.load(getClass().getResource("/VsScene/VsScence.fxml"));
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
    public void saveEmailClick(MouseEvent event) {
        if(oldEmailTF.getText().trim().isEmpty()||newEmailTF.getText().trim().isEmpty()||confirmEmailTF.getText().trim().isEmpty()) {
            emailErr.setText("Please enter your Email!");
            emailVbox.setVisible(true);
        }
        else if(!newEmailTF.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            emailErr.setText("Invalid Email!");
            emailVbox.setVisible(true);
        }
        else if(!oldEmailTF.getText().equals(newEmailTF.getText())){
            emailErr.setText("Not matched Email");
            emailVbox.setVisible(true);
        }
        else
        {
            emailErr.setText("");
            emailVbox.setVisible(false);
        }
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profileStub = Rmi.getProfileStub();
        try {
            oldEmailTF.setText(profileStub.getUserData(1).getEmail());
            currentScore.setText(String.valueOf(profileStub.getUserData(1).getScore()));
            nickNameLabel.setText(profileStub.getUserData(1).getNickName());
            emailVbox.setVisible(true);
            
            

        } catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
