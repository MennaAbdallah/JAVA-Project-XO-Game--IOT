package GameOnline;

import DTO.GameOnlineClass.GamePlay;
import DTO.GameOnlineClass.MessagePayload;
import RMI.Rmi;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import sun.audio.AudioPlayer;
import xogameserver.DBManagment;
/**
 *
 * @author mina
 */
public class GameController implements Initializable {
    DBManagment db = DBManagment.getInstance();
    static int type = 1;
    static int playerID = 1;
    int gameID = 1;
    volatile int newPlace = 0;
    volatile MouseEvent eventButton = null;
    Game game = new Game();
    GamePlay gamePlaySend = new GamePlay();
    GamePlay gamePlayRecieve = new GamePlay();
    MessagePayload messageRec=new MessagePayload();
    MessagePayload messageSend=new MessagePayload();
    @FXML
    AnchorPane pauseAnchor, mainAnchor, textAnchor;
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
        
    public Button button;

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
 /*       new Thread(() -> {
            checkOnMessage();
        }).start();
*/
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
    private void pauseAction(MouseEvent event){
        pauseAnchor.setVisible(true);
        BoxBlur boxBlur = new BoxBlur();
        mainAnchor.setEffect(boxBlur);
        db.setBoardStatus(gameID, Arrays.toString(game.getXoGame()));
    }
    
    
    @FXML
    private void resumeClick(MouseEvent event){
        pauseAnchor.setVisible(false);
        mainAnchor.setEffect(null);
        db.getBoardStatus(gameID);
    }
    @FXML

    public void Send(ActionEvent actionEvent) {
           messageSend.setGameID(gameID);
           messageSend.setPlayerID(playerID);
           Text text=new Text(textField.getText());
           text.setStyle("-fx-font-fill:#FF0000");
           textFlow.getChildren().add(text);
           messageSend.setMessage(textField.getText());
           
        try {
            Rmi.getstubGame().sendMessage(messageSend);
            Rmi.getstubGame().resetMessage();
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
    }

    private void checkOnMessage(){
        while(true){
        try{
            messageRec=Rmi.getstubGame().getMessage();
            if(messageRec!=null&&messageRec.getGameID()==gameID&&messageRec.getPlayerID()!=playerID){
                    String Message=messageRec.getMessage();
                    Text text=new Text(Message);
                    text.setStyle("-fx-font-fill:#0000FF");
                    textFlow.getChildren().add(text);
            }
        }
        catch(RemoteException ex){
            System.out.println("Error in Get Message ");
        }
        }
  
        
    }
}
