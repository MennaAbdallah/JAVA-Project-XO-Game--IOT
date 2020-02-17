/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupscene;

import DTO.SimpleUser;
import DTO.User;
import LoginScene.Main;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import xogameserver.interfaces.LoginInterface;

/**
 *
 * @author menna
 */
public class SignUpController implements Initializable {

    @FXML
    private Label nameErr, nickNameErr, passwordErr, confirmErr, emailErr;
    @FXML
    private TextField nameTF, emailTF, nickNameTF;
    @FXML
    private PasswordField passwordTF, confirmPasswordTF;
    private LoginInterface stub;


    @FXML
    private void signUpClick(ActionEvent event) {
        boolean flag = true;
        if (nameTF.getText().trim().isEmpty()) {
            nameErr.setText("Please enter your name!");
            flag = false;
        } else {
            nameErr.setText("");
        }

        if (nickNameTF.getText().trim().isEmpty()) {
            nickNameErr.setText("Please enter your Nickname!");
            flag = false;
        } else {
            nickNameErr.setText("");
        }
        if (emailTF.getText().trim().isEmpty()) {
            emailErr.setText("Please enter your Email!");
            flag = false;
        } else if (!emailTF.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            emailErr.setText("Invalid Email!");
            flag = false;
        } else {
            emailErr.setText("");
        }

        if (passwordTF.getText().trim().isEmpty()) {
            passwordErr.setText("Please enter password!");
            flag = false;
        } else if (!passwordTF.getText().matches("(\\w{8,})")) {
            passwordErr.setText("Invalid password");
            flag = false;
        } else {
            passwordErr.setText("");
        }

        if (confirmPasswordTF.getText().trim().isEmpty()) {
            confirmErr.setText("Please confirm your password!");
            flag = false;
        } else if (!passwordTF.getText().equals(confirmPasswordTF.getText())) {
            confirmErr.setText("Not matched password!");
            flag = false;

        } else {
            confirmErr.setText("");
        }
        networkSignUp(flag);
    }
    public void changeSceneVS() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/VsScene/VsScence.fxml"));
            Main.getMyStage().setTitle("TicTacToe");
            Main.getMyStage().setResizable(false);
            Main.getMyStage().setScene(new Scene(root, 909, 509));
            Main.getMyStage().show();
        }
        catch(Exception IOException){
            System.err.println("Error in Change Scence");
        }
    }
      public void changeSceneLogin() {
        try{
                Parent root = FXMLLoader.load(getClass().getResource("/LoginScene/login.fxml"));
                Scene scene = new Scene(root, 909, 509);
                Main.getMyStage().setTitle("TicTacToe");
                Main.getMyStage().setResizable(false);
                Main.getMyStage().setScene(scene);
                Main.getMyStage().show();
        }
        catch(Exception IOException){
            System.err.println("Error in Change Scence");
        }
    }
    public boolean networkSignUp(boolean flag){
        try{
            if(flag==true){
              User user=new User();
              user.setEmail(emailTF.getText());
              user.setPassword(passwordTF.getText());
              user.setUserName(nameTF.getText());
              user.setNickName(nickNameTF.getText()); 
              System.out.println("Email : "+emailTF.getText()+" , Pass : "+passwordTF.getText()+" , Name : "
                        +nameTF.getText()+" , NName : "+nickNameTF.getText());
              boolean check=Main.getStubSignUp().signUp(user);
              if(check==true){
                  changeSceneLogin();
              }
            }
            else{
                System.out.println("Error in Signup Menu");
            }
        }
        catch(Exception RemoteException){
            System.err.println("Error From Connection Stub2");
        }
       
        
        return false;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
