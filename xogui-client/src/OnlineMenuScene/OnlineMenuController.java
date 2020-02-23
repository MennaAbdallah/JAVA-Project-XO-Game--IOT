/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineMenuScene;

import DTO.GameOnlineClass.GameHandler;
import DTO.InvitationPayload;
import DTO.SimpleUser;
import GameOnline.GameController;
import LoginScene.LoginController;

import LoginScene.Main;
import LoginScene.MusicPlayer;
import LoginScene.UserData;
import RMI.Rmi;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author menna
 */
public class OnlineMenuController implements Initializable {

    private int gameID = 0;

    @FXML
    private Label label;

    @FXML
    private GridPane gPane;

    public ImageView IMute;
    public ImageView INoMute;

    private int invitationSent;

    /**
     *
     * @param v
     */
    public void sort(Vector<SimpleUser> v) {
        Collections.sort(v,
                (SimpleUser user1, SimpleUser user2)
                -> user1.getScore() > user2.getScore() ? -1 : user1.getScore() == user2.getScore() ? 0 : 1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nick;
        try {
            Rmi.getInvitationStub().sendInvitation(-1,"demo", -1);
        } catch (RemoteException ex) {
            Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Vector<SimpleUser> v = Rmi.getInvitationStub().getUsers();
            sort(v);
            for (int x = 0; x < v.size(); x++) {

                Label lUserName = new Label();
                lUserName.setId("lUserName" + x);
                nick = v.get(x).getNickName();
                if (nick.equals(UserData.getSimpleUser().getNickName())) {
                    nick = "Me";
                }
                lUserName.setText(nick);
                gPane.add(lUserName, 0, x);

                Label lScore = new Label();
                lScore.setId("lScore" + x);
                lScore.setText("" + v.get(x).getScore());
                gPane.add(lScore, 1, x);

                if (v.get(x).getStatus() == 1 && !"Me".equals(nick)) {
                    Button lInvitaion = new Button();
                    lInvitaion.setId(Integer.toString(v.get(x).getId()));
                    lInvitaion.setText("Online");
                    gPane.add(lInvitaion, 2, x);
                    System.out.println(v.get(x).getId());
                    lInvitaion.setOnAction(event -> {
                        invitationSent++;
                        int id = Integer.parseInt(((Button) event.getSource()).getId());
                        for (SimpleUser simpleUser : v) {
                            if (simpleUser.getId() == id) {
                                sendInvitation(id, simpleUser.getNickName());
                            }
                        }
                    });
                }
            }

            getInvited();

        } catch (RemoteException ex) {
            System.err.println("Client exception: " + ex.toString());
            ex.printStackTrace();
        }

    }

    public void musicControl(ActionEvent actionEvent) {
        MusicPlayer.checkStatus(IMute, INoMute);

    }

    public void GoBack(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/VsScene/VsScence.fxml"));
            Scene scene = new Scene(root, 909, 509);
            Main.getMyStage().setTitle("TicTacToe");
            Main.getMyStage().setResizable(false);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().show();
        } catch (Exception IOException) {
            System.err.println("Error in Change Scence");
        }

    }

    public void sendInvitation(int rcvId, String nickName) {
        if (gameID == 0) {

            try {
                gameID = Rmi.getInvitationStub().sendInvitation(UserData.getSimpleUser().getId(), nickName, rcvId);
                getMyInvitationState(gameID,rcvId);
            } catch (RemoteException ex) {
                Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void getInvited() {
        new Thread(() -> {
            while (true) {
                try {
                    InvitationPayload invitationPayload = Rmi.getInvitationStub().getNewInvitation();
                    if (invitationPayload != null) {
                        if (invitationPayload.getRecID() == UserData.getSimpleUser().getId()) {
                            invitePopUp(invitationPayload.getSenderName(), invitationPayload.getGameID(), invitationPayload.getRecID());
                            break;
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    private void getMyInvitationState(int gameID1, int rcvId) {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(gameID1);
                    System.out.println(rcvId);

                    GameHandler state = Rmi.getInvitationStub().getMyInvitationState(gameID1);
                    if (state.getSecondPlayerID() == rcvId) {
                        GameController.setType(1);
                        Platform.runLater(() -> {
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("/GameOnline/GameScene.fxml"));
                                Scene scene = new Scene(root);
                                Main.myStage.setTitle("TicTacToe");
                                Main.myStage.setResizable(false);
                                Main.myStage.setScene(scene);
                                Main.myStage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        break;
                    } else if (state.getSecondPlayerID() == -2) {
                        //////////////////////////
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }

    private void invitePopUp(String userName, int gameID, int userID) {
        Platform.runLater(() -> {
            try {
                Stage s1 = new Stage();
                s1.initModality(Modality.WINDOW_MODAL);
                s1.initOwner(Main.myStage);
                Parent root = FXMLLoader.load(getClass().getResource("/InvationScene/FXMLDocument.fxml"));
                Scene scene = new Scene(root);

                Button acceptBtn = (Button) scene.lookup("#accept");
                Button rejectBtn = (Button) scene.lookup("#reject");
                Label l = (Label) scene.lookup("#label");
                l.setText(userName);

                acceptBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Rmi.getInvitationStub().acceptInvitation(gameID, userID);
                            GameController.setType(-1);
                            Parent root = FXMLLoader.load(getClass().getResource("/GameOnline/GameScene.fxml"));
                            Scene scene = new Scene(root);
                            Main.myStage.setTitle("TicTacToe");
                            Main.myStage.setResizable(false);
                            Main.myStage.setScene(scene);
                            Main.myStage.show();
                            s1.close();
                        } catch (RemoteException ex) {
                            Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                );

                rejectBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Rmi.getInvitationStub().refuseInvitation(gameID, userID);
                        } catch (RemoteException ex) {
                            Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                );

                s1.setTitle("TicTacToe");
                s1.initStyle(StageStyle.UNDECORATED);
                s1.setResizable(false);
                s1.setScene(scene);

                s1.show();

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
