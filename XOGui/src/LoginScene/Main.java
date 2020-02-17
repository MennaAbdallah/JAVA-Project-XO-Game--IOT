package LoginScene;

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
    
     private static LoginInterface stub;
     private static SignUp stub2;
    public static LoginInterface getStub() {
        return stub;
    }
    public static SignUp getStubSignUp() {
        return stub2;
    }
    public static Stage myStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        myStage=primaryStage;
        try {
            // Getting the registry
             Registry registry = LocateRegistry.getRegistry("127.0.0.1",5005);
             // Looking up the registry for the remote object
             stub = (LoginInterface) registry.lookup("Hello");
             stub2=(SignUp) registry.lookup("SignUP");
            // Calling the remote method using the obtained object
        }
        catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
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




