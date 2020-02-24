/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import xogameserver.ServerImplemantion.GameImp;
import xogameserver.ServerImplemantion.InvitationImp;
import xogameserver.ServerImplemantion.LoginImplem;
import xogameserver.ServerImplemantion.MessageImp;
import xogameserver.ServerImplemantion.ProfileImp;
import xogameserver.ServerImplemantion.SignUpImplem;

/**
 * FXML Controller class
 *
 * @author Mashael
 */
public class OnOFFController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane BackGround;

    @FXML
    private Label label;

    @FXML
    private ToggleButton on;

    @FXML
    private ToggleButton off;

    ToggleGroup tg = new ToggleGroup();

    @FXML
    Label Massage;
    @FXML
    TextField ip,port;
    private Registry connectorRG = null;

    public void OnBtn(ActionEvent actionEvent) {
        if(ip.getText().isEmpty()||port.getText().isEmpty()){
          Massage.setText("Check IP and Port");

        }
        else if (connectorRG == null) {
            System.setProperty("java.rmi.server.hostname", ip.getText());

            System.setProperty("java.security.policy", "file:./src/xogameserver/security.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            try {
//             GameHandler gameHandler= new GameHandler();
//             gameHandler.setGameID(1);
//             gameHandler.setFirstPlayerID(1);
//             gameHandler.setSecondPlayerID(2);
//             GameHandlersList.addGameHandler(gameHandler);
//             
                // Instantiating the implementation class 
                LoginImplem loginStub = new LoginImplem();
                SignUpImplem signStub = new SignUpImplem();
                GameImp gameStub = new GameImp();
                MessageImp messageStub = new MessageImp();
                InvitationImp invitationImp = new InvitationImp();
                ProfileImp profileImp = new ProfileImp();

                // Binding the remote object (stub) in the registry 
                connectorRG = LocateRegistry.createRegistry(Integer.parseInt(port.getText()));
                if(connectorRG!=null){
                     connectorRG.bind("login", loginStub);
                connectorRG.bind("signup", signStub);
                connectorRG.bind("game", gameStub);
                //registry.bind("message", messageStub);
                connectorRG.bind("profile", profileImp);
                connectorRG.bind("invite", invitationImp);
                BackGround.setStyle("-fx-background-color: #FFD9A5;");
                     Massage.setText("Connected");
                System.err.println("Server ready");
                }else{
                    Massage.setText("Error in connection");
                }
               
            } catch (Exception e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }
            
           
        }

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
