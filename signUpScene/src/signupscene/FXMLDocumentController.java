/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupscene;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.util.regex.*;  
/**
 *
 * @author menna
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label nameErr,nickNameErr,passwordErr,confirmErr,emailErr ;
    @FXML
    private TextField nameTF, emailTF,nickNameTF;
    @FXML
    private PasswordField passwordTF,confirmPasswordTF;
    @FXML
    private void signUpClick(ActionEvent event) {
        if(nameTF.getText().trim().isEmpty()) {
            nameErr.setText("Please enter your name!");
        }
        else
        {
            nameErr.setText("");
        }
        
        if(nickNameTF.getText().trim().isEmpty()) {
            nickNameErr.setText("Please enter your Nickname!");
        }
        else
        {
            nickNameErr.setText("");
        }
        if(emailTF.getText().trim().isEmpty()) {
            emailErr.setText("Please enter your Email!");
        }
        else
        {
            emailErr.setText("");
        }
        
        if(passwordTF.getText().trim().isEmpty()) {
            passwordErr.setText("Please enter password!");
        }
        else if(!passwordTF.getText().matches("\\w{8,}")){
            passwordErr.setText("The password must be 8 characters or longer!");
        }
        else
        {
            passwordErr.setText("");
        }

            System.out.print(passwordTF.getText() + "\n");
            System.out.print(confirmPasswordTF.getText() + "\n");
            System.out.print((passwordTF.getText() == confirmPasswordTF.getText()) + "\n");
        if(confirmPasswordTF.getText().trim().isEmpty()) {
            confirmErr.setText("Please confirm your password!");
        }
        else if(!passwordTF.getText().equals(confirmPasswordTF.getText()))
        {
            confirmErr.setText("Not matched password!");
        }
        else 
        {
            confirmErr.setText("");
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
