/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profile;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
/**
 *
 * @author menna
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public VBox passwdVbox ,emailVbox;
    
    @FXML
    private PasswordField oldPWTF,newPWTF,confirmPWTF;
            
    @FXML
    private TextField oldEmailTF,newEmailTF,confirmEmailTF;
            
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
        // TODO
    }    
    
}
