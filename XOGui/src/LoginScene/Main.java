package LoginScene;

import xogameserver.interfaces.LoginInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
     private static LoginInterface stub;
    public static LoginInterface getStub() {
        return stub;
    }        
    @Override
    public void start(Stage primaryStage) throws Exception{
    try {
            // Getting the registry
             Registry registry = LocateRegistry.getRegistry("192.168.1.3",5030);
            // Looking up the registry for the remote object
             stub = (LoginInterface) registry.lookup("Hello");
            // Calling the remote method using the obtained object
        }
        catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 909, 509);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}




