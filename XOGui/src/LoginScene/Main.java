package LoginScene;

import RMI.Rmi;
import java.io.File;
import xogameserver.interfaces.LoginInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import xogameserver.interfaces.SignUp;

/**
 *
 * @author fegoo
 */

public class Main extends Application {
  
   public static Stage myStage;
   public static boolean connected;
//      Main(){
//          connected=false;
//          Rmi.setIp("127.0.0.1");
//          Rmi.setPort(5005);
//        }
    
        String path ="C:\\Users\\fegoo\\Desktop\\JAVA-Project-XO-Game--IOT\\XOGui\\src\\Song1.mp3";
    Media media = new Media (new File (path).toURI().toString());
    MediaPlayer mediaplayer = new MediaPlayer(media);

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
