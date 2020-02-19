package LoginScene;

import RMI.Rmi;
import xogameserver.interfaces.LoginInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xogameserver.interfaces.SignUp;

public class Main extends Application {
  
   public static Stage myStage;
   public static boolean connected;
//      Main(){
//          connected=false;
//          Rmi.setIp("127.0.0.1");
//          Rmi.setPort(5005);
//        }
    @Override
    public void start(Stage primaryStage) throws Exception{
        myStage=primaryStage;
        System.out.println("First line");
        Rmi.connectedSevrver();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 909, 509);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static Stage getMyStage() {
        return myStage;
    }
}




