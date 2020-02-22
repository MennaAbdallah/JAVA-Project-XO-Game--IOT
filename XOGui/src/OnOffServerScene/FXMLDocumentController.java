/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnOffServerScene;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author fegoo
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private AnchorPane BackGround;
    
    
     @FXML
    private Label label;
    

       @FXML
           private ToggleButton on;

     @FXML
         private ToggleButton off;
     
        ToggleGroup tg = new ToggleGroup();

         
   
    
        public void OnBtn(ActionEvent actionEvent) {
                
        BackGround.setStyle("-fx-background-color: #FFD9A5;");
            
            
    }
        
        
        
                public void OffBtn(ActionEvent actionEvent) {
                
BackGround.setStyle("-fx-background-color:  #FF7F7F;");
            
            
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       on.setToggleGroup(tg);
      off.setToggleGroup(tg);
      on.setSelected(true);
      
    }    
    
}
