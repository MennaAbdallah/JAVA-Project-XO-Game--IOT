package DTO.GameOnlineClass;

import xogameserver.ServerImplemantion.GameImp;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname", "192.168.1.25");
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

        System.setProperty("java.security.policy","file:/src/DTO/GameOnlineClass/security.policy");
        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }


        try {
            GameHandler gameHandler = new GameHandler();
            GameImp gameStub = new GameImp();
            gameHandler.gameID=1;
            gameHandler.firstPlayerID=1;
            gameHandler.secondPlayerID=2;
            GameHandlersList.addGameHandler(gameHandler);

            Registry registry = LocateRegistry.createRegistry(5000);
            registry.bind("game",gameStub);

            System.out.println(GameHandlersList.getGameHandler(1).firstPlayerID);
            System.out.println("Server Ready");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }    }
}
