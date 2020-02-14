/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverinterface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.regex.*;  

/**
 *
 * @author menna
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label serverIPErr, serverPortErr, DBHostErr ,DBPortErr;
    
    @FXML
    private TextField serverIPTF,serverPortTF, DBHostTF, DBPortTF;
    
    @FXML
    private void connectClick(MouseEvent event) {
        if(!serverIPTF.getText().matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")){
            serverIPErr.setText("Invalid IP");
        }
        else{
            serverIPErr.setText("");
        }
        
        if(!DBHostTF.getText().matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")){
            DBHostErr.setText("Invalid IP");
        }
        else{
            DBHostErr.setText("");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
