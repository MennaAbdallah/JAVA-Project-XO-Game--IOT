package gameabdo;

import java.util.HashMap;

public class GameHandlersList {
    private static final HashMap<Integer,GameHandler> games=new HashMap<>();

    public static GameHandler getGameHandler(int gameID){
        return games.get(gameID);
    }

    public static void addGameHandler(GameHandler gameHandler){
        games.put(gameHandler.gameID,gameHandler);
    }
    public static void removeGameHandler(GameHandler gameHandler){
        GameHandler gameHandlerRemove = games.get(gameHandler.gameID);
        games.remove(gameHandlerRemove);
    }
    public static void removeGameHandlerByGameID(int gameID){
        games.remove(gameID);
    }
    public static int getGameID(int sideID){
        final int[] gameID = new int[1];
        games.forEach((key,gameHandler)->{
            if(gameHandler.firstPlayerID==sideID||gameHandler.secondPlayerID==sideID){
                gameID[0] = gameHandler.gameID;
            }
        });
        return gameID[0];
    }
}
