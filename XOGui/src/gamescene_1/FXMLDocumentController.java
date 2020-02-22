/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamescene_1;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author menna
 */
public class FXMLDocumentController implements Initializable {
    Game game = new Game();
    int turn;
    @FXML
    private static final Integer STARTTIME = 15;
    public Label timelabel;
    @FXML
    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    @FXML
    private Label lbl00,lbl01,lbl02,lbl10,lbl11,lbl12,lbl22,lbl21,lbl20;
    
    @FXML
    private Button button00,button01,button02,button10,button11,button12,button22,button21,button20;
    
    public Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timelabel.textProperty().bind(timeSeconds.asString());
        if (game.getPlayerTurn() == 1) playAi();
    }

    @FXML
    private void button00click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(0, 0, -1);
            lbl00.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    @FXML
    private void button01click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(0, 1, -1);
            lbl01.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    
    @FXML
    private void button02click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(0, 2, -1);
            lbl02.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    @FXML
    private void button10click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(1, 0, -1);
            lbl10.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    @FXML
    private void button11click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(1, 1, -1);
            lbl11.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    @FXML
    private void button12click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(1, 2, -1);
            lbl12.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    @FXML
    private void button20click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(2, 0, -1);
            lbl20.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    @FXML
    private void button21click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(2, 1, -1);
            lbl21.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    @FXML
    private void button22click(MouseEvent event){
        if (game.getPlayerTurn() != 1
                && !game.isGameOver()
                && game.hasWinner() == 0)
        {
            game.insertMoveXY(2, 2, -1);
            lbl22.setText("O");
            ((Button)event.getSource()).setDisable(true);
            playAi();
        }
    }
    
    public void playAi()
    {
        if (!game.isGameOver() && game.hasWinner() == 0)
        {
            minimax.Move bestMove = minimax.findBestMove(game.getXoGame());
            game.insertMoveXY(bestMove.row, bestMove.col, 1);
            
            if (bestMove.row == 0 && bestMove.col == 0)
            {
                lbl00.setText("X");
                button00.setDisable(true);
            }
            else if (bestMove.row == 1 && bestMove.col == 0)
            {
                lbl10.setText("X");
                button10.setDisable(true);
            }
            else if (bestMove.row == 2 && bestMove.col == 0)
            {
                lbl20.setText("X");
                button20.setDisable(true);
            }
            else if (bestMove.row == 0 && bestMove.col == 1)
            {
                lbl01.setText("X");
                button01.setDisable(true);
            }
            else if (bestMove.row == 1 && bestMove.col == 1)
            {
                lbl11.setText("X");
                button11.setDisable(true);
            }
            else if (bestMove.row == 2 && bestMove.col == 1)
            {
                lbl21.setText("X");
                button21.setDisable(true);
            }
            else if (bestMove.row == 0 && bestMove.col == 2)
            {
                lbl02.setText("X");
                button02.setDisable(true);
            }
            else if (bestMove.row == 1 && bestMove.col == 2)
            {
                lbl12.setText("X");
                button12.setDisable(true);
            }
            else if (bestMove.row == 2 && bestMove.col == 2)
            {
                lbl22.setText("X");
                button22.setDisable(true);
            }
            else
            {
    //            throw new Exception("are you kidding me?");
            }
        }
    }
  
        
            
    public void start(ActionEvent actionEvent) {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }   
    
     public void Send(ActionEvent actionEvent) {
         
     }
         
     }
    
    

