package DTO.GameOnlineClass;

import java.util.HashMap;

public class GameSidesList {
    private static final HashMap<Integer,GameSide> gameSideHashMap= new HashMap<>();

    public static void addGameSide(int sideID,GameSide gameSide){
        gameSideHashMap.put(sideID,gameSide);
    }
    public static GameSide getGameSide(int sideID){
        return gameSideHashMap.get(sideID);
    }

    public static void removeGameSide(int sideID){
        gameSideHashMap.remove(sideID);
    }

}
