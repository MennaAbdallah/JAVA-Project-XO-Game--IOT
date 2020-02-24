package GameOnline;
import LoginScene.Main;
import DTO.GameOnlineClass.GamePlay;
import DTO.GameOnlineClass.MessagePayload;
import LoginScene.UserData;
import RMI.Rmi;
import gamescene_ai.GameAiController;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import sun.audio.AudioPlayer;

/**
 *
 * @author mina
 */
public class GameController implements Initializable {

    static int type = -1;
    static int playerID = UserData.getSimpleUser().getId();
    int gameID = 0;
    volatile int newPlace = 0;
    volatile MouseEvent eventButton = null;
    Game game = new Game();
    GamePlay gamePlaySend = new GamePlay();
    GamePlay gamePlayRecieve = new GamePlay();
    MessagePayload messageRec = new MessagePayload();
    MessagePayload messageSend = new MessagePayload();

    @FXML
    private static final Integer STARTTIME = 15;
    public Label timelabel;
    @FXML
    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    @FXML
    private Label lbl00, lbl01, lbl02, lbl10, lbl11, lbl12, lbl22, lbl21, lbl20;
    //private Label label[]=new Label[8];

    HashMap<Integer, Label> labelsHash = new HashMap<>();

    @FXML
    private Button button00, button01, button02, button10, button11, button12, button22, button21, button20;
    //private Button buttonGame[]=new Button[8];
    @FXML
    private TextField textField;
    @FXML
    private TextFlow textFlow;
    
    @FXML
    private Label Massage;
    @FXML
    private Label Score;

    private Button button;

    public void changeToWinner() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/winscene/WinScene.fxml"));
            Scene scene = new Scene(root);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().show();
        } catch (IOException ex) {
            Logger.getLogger(GameAiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeToLoser() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/losescene/LoseScene.fxml"));
            Scene scene = new Scene(root);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().show();
        } catch (IOException ex) {
            Logger.getLogger(GameAiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeToGameOver() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/game_over/GameOver.fxml"));
            Scene scene = new Scene(root);
            Main.getMyStage().setScene(scene);
            Main.getMyStage().show();
        } catch (IOException ex) {
            Logger.getLogger(GameAiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelsHash.put(1, lbl00);
        labelsHash.put(2, lbl10);
        labelsHash.put(3, lbl20);
        labelsHash.put(4, lbl01);
        labelsHash.put(5, lbl11);
        labelsHash.put(6, lbl21);
        labelsHash.put(7, lbl02);
        labelsHash.put(8, lbl12);
        labelsHash.put(9, lbl22);
        timelabel.textProperty().bind(timeSeconds.asString());
        try {
            gameID = Rmi.getstubGame().getGameID(playerID);

        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Score.setText("Score : "+UserData.getSimpleUser().getScore());
        Score.setVisible(true);
        /*new Thread(() -> {
         checkOnMessage();
         }).start();*/

        new Thread(() -> {
            gameLogic();
        }).start();

    }

    @FXML
    private void button00click(MouseEvent event) {
        //Play x=gameSideObj.getPlay();
        System.out.println("button00click");
        newPlace = 1;
        eventButton = event;
    }

    @FXML
    private void button01click(MouseEvent event) {
        System.out.println("button01click");
        newPlace = 4;
        eventButton = event;

    }

    @FXML
    private void button02click(MouseEvent event) {
        System.out.println("button02click");
        newPlace = 7;
        eventButton = event;
    }

    @FXML
    private void button10click(MouseEvent event) {
        System.out.println("button10click");
        newPlace = 2;
        eventButton = event;

    }

    @FXML
    private void button11click(MouseEvent event) {
        System.out.println("button11click");
        newPlace = 5;
        eventButton = event;

    }

    @FXML
    private void button12click(MouseEvent event) {
        System.out.println("button12click");
        newPlace = 8;
        eventButton = event;
    }

    @FXML
    private void button20click(MouseEvent event) {
        System.out.println("button20click");
        newPlace = 3;
        eventButton = event;
    }

    @FXML
    private void button21click(MouseEvent event) {
        System.out.println("button21click");
        newPlace = 6;
        eventButton = event;
    }

    @FXML
    private void button22click(MouseEvent event) {
        System.out.println("button22click");
        newPlace = 9;
        eventButton = event;
    }

    @FXML

    public void Send(ActionEvent actionEvent) {
        messageSend.setGameID(gameID);
        messageSend.setPlayerID(playerID);
        Text text = new Text(textField.getText() + "\n");
        text.setStyle("-fx-font-fill:#FF0000");
        textFlow.getChildren().add(text);
        messageSend.setMessage(textField.getText());

        try {
            Rmi.getstubGame().sendMessage(messageSend);
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start(ActionEvent actionEvent) {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + 1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }
    private void gameLogic() {
        while (game.hasWinner() == 0) {
            if (game.isGameOver()) {
                break;
            }
            System.out.println(game.getPlayerTurn());
            if (game.getPlayerTurn() == type) {
                System.out.println("Enter Loop");
                Massage.setText("Your Are Turn : ");
                Massage.setVisible(true);
                while (newPlace == 0);
                System.out.println("Exit Loop1");
                game.insertMove(newPlace, type);
                Platform.runLater(() -> {
                    if (type == 1) {
                        labelsHash.get(newPlace).setText("x");
                        System.out.println("x");
                    } else {
                        labelsHash.get(newPlace).setText("o");
                        System.out.println("o");
                    }
                    ((Button) eventButton.getSource()).setDisable(true);
                });

                try {
                    gamePlaySend.setGameID(gameID);
                    gamePlaySend.setPlayerID(playerID);
                    gamePlaySend.setNewPlace(newPlace);
                    Rmi.getstubGame().setPlay(gamePlaySend);
                } catch (RemoteException ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //TODO : gameSideObj.getGameID(1)==>1==>Player ID come from obj from Server Status online Scence
                //TODO : 1==>Player ID 
                newPlace = 0;
                System.out.println("Exit All Loop");

            } else {
                Massage.setVisible(false);
                while (true) {
                    if (game.hasWinner() != 0 || game.isGameOver()) {
                        break;
                    }
                    try {
                        gamePlayRecieve = Rmi.getstubGame().getPlay();
                    } catch (RemoteException ex) {
                        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (gameID == gamePlayRecieve.getGameID() && playerID != gamePlayRecieve.getPlayerID()) {

                        Platform.runLater(() -> {
                            if (type == 1) {
                                game.insertMove(gamePlayRecieve.getNewPlace(), -1);
                                labelsHash.get(gamePlayRecieve.getNewPlace()).setText("o");
                                System.out.println("o");
                            } else {
                                game.insertMove(gamePlayRecieve.getNewPlace(), 1);
                                labelsHash.get(gamePlayRecieve.getNewPlace()).setText("x");
                                System.out.println("x");
                            }
                        });

                        break;
                    }

                }

            }

        }
        System.out.println("Exit From While : ");
        System.out.println("Has winner"+ game.hasWinner());
        
        if(game.hasWinner()==type){
            int score =UserData.getSimpleUser().getScore()+10;
            System.out.println("changeToWinner() : Score  : "+score);
            UserData.getSimpleUser().setScore(score);
            try {
                Rmi.getstubGame().setWinner(playerID,score);
            } catch (RemoteException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.runLater(() -> {
                   changeToWinner();
            });
        }else if(game.hasWinner()==0){
            System.out.println("changeToGameOver()");
            Platform.runLater(() -> {
                changeToGameOver();
            });
        }else{
            System.out.println("changeToGameOver()");
            Platform.runLater(() -> {
                changeToLoser();
                });
        }
//            if (game.hasWinner() == 1) {
//                if(type==1){
//                    System.out.println("changeToWinner()");
//                    changeToWinner();
//                }
//                else{
//                    System.out.println("changeToLoser()");
//                    changeToLoser();
//                }
//                
//            }
//            if (game.hasWinner() == -1) {
//                
//                if(type==-1){
//                    System.out.println("changeToWinner()");
//                    changeToWinner();
//                }
//                else{
//                    System.out.println("changeToLoser()");
//                    changeToLoser();
//                }
//            }
//            if (game.isGameOver() && game.hasWinner() == 0) {
//                System.out.println("changeToGameOver()");
//                changeToGameOver();
//            }
    }

    private void checkOnMessage() {
        while (true) {
            try {
                messageRec = Rmi.getstubGame().getMessage();
                if (messageRec != null && messageRec.getGameID() == gameID && messageRec.getPlayerID() != playerID) {
                    String Message = messageRec.getMessage();
                    Rmi.getstubGame().resetMessage();
                    Text text = new Text(Message + "\n");
                    text.setStyle("-fx-font-fill:#0000FF");
                    Platform.runLater(() -> {
                        textFlow.getChildren().add(text);
                    });

                }
            } catch (RemoteException ex) {
                System.out.println("Error in Get Message ");
            }
        }

    }

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        GameController.type = type;
    }
    
    
}
