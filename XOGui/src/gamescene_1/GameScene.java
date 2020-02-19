/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamescene_1;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author menna
 */
public class GameScene extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("TicTacToe");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 909, 509));
        primaryStage.show();
    }

    private static int getXYofMove(int x , int y ) {

        int cellNumber=0;

        if(x==0 && y==0){
            return 1;
        }else if(x==0 && y==1){
            return 2;
        }else if(x==0 && y==2){
            return 3;
        }else if(x==1 && y==0){
            return 4;
        }else if(x==1 && y==1){
            return 5;
        }else if(x==1 && y==2){
            return 6;
        }else if(x==2 && y==0){
            return 7;
        }else if(x==2 && y==1){
            return 8;
        }else if(x==2 && y==2){
            return 9;
        }

        return cellNumber;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
