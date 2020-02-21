package gameabdo;

public class gameImp implements gameInterface {
    @Override
    public void addGameSide(int sideID,GameSide gameSide) {
        GameSidesList.addGameSide(sideID,gameSide);
    }
}
