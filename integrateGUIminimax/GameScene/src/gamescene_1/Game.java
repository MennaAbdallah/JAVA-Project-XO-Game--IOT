/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamescene;

import java.util.Arrays;

/**
 *
 * @author menna
 */
public class Game {
    
    private final int[][] xoGame = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    private final boolean[][] xoGameAvailable = {{true,true,true},{true,true,true},{true,true,true}};
    private boolean xWinner = false, oWinner = false;
    private int playerTurn = -1;

    /**** hasWinner method
     *
     * @return 0 = no winner
     *         1 = x winner
     *         -1 = y winner
     *******/
    public int hasWinner() {
        int win = 0;
        if (xWinner) {
            win = 1;
        } else if (oWinner) {
            win = -1;
        }
        return win;
    }


    public boolean insertMove(int cellNumber , int player) {

        XYReplication xy = getXYofMove(cellNumber);
        int x = xy.getX();
        int y = xy.getY();

        return movesManager(x, y,player);

    }

    public boolean insertMoveXY(int x ,int y , int player){

        return movesManager(x,y, player);
    }

    private boolean movesManager(int x, int y, int player) {

        if(xoGameAvailable[x][y]) {
            if (player < -1 || player > 1) {
                throw new IndexOutOfBoundsException("insert 1 for x and -1 for o");
            }

            if(playerTurn==1){
                playerTurn=-1;
            }else {
                playerTurn=1;
            }

            xoGame[x][y] = player;
            xoGameAvailable[x][y]=false;
            // horizontal check for winner
            horizontalChecker();

            // vertical check for winner
            verticalChecker();

            int diagonalCheck1 = xoGame[0][0] + xoGame[1][1] + xoGame[2][2];
            int diagonalCheck2 = xoGame[0][2] + xoGame[1][1] + xoGame[2][0];
            checkerExtenstion(diagonalCheck1);
            checkerExtenstion(diagonalCheck2);
            return true;
        }
        return false;
    }

    private void horizontalChecker() {
        for (int xIdx = 0; xIdx < 3; xIdx++) {
            int check = 0;

            for (int yIdx = 0; yIdx < 3; yIdx++) {
                check += xoGame[xIdx][yIdx];
            }

            checkerExtenstion(check);
        }
    }

    private void verticalChecker() {
        for (int xIdx = 0; xIdx < 3; xIdx++) {
            int check = 0;

            for (int yIdx = 0; yIdx < 3; yIdx++) {
                check += xoGame[yIdx][xIdx];
            }

            checkerExtenstion(check);
        }
    }

    private void checkerExtenstion(int check) {
        switch (check) {
            case 3:
                xWinner = true;
                break;
            case -3:
                oWinner = true;
                break;
        }
    }

    public int getPlayerTurn(){
        return playerTurn;
    }
    public boolean isGameOver(){
        boolean gameOver = true;
        for (int xIdx = 0; xIdx < 3; xIdx++) {

            for (int yIdx = 0; yIdx < 3; yIdx++) {
                if (xoGameAvailable[xIdx][yIdx]) {
                    gameOver = false;
                    break;
                }
            }

        }
        System.out.println(Arrays.deepToString(xoGame));
        System.out.println(Arrays.deepToString(xoGameAvailable));
        return gameOver;
    }

    public int[][] getXoGame() {
        return xoGame;
    }

    
    private static XYReplication getXYofMove(int cellNumber) {
        int x = 0;
        int y = 0;

        if (cellNumber > 9 || cellNumber < 1) {
            throw new IndexOutOfBoundsException("Insert Number form 1 to 9 ");
        }

        switch (cellNumber) {
            case 1:
                x = 0;
                y = 0;
                break;
            case 2:
                x = 0;
                y = 1;
                break;
            case 3:
                x = 0;
                y = 2;
                break;
            case 4:
                x = 1;
                y = 0;
                break;
            case 5:
                x = 1;
                y = 1;
                break;
            case 6:
                x = 1;
                y = 2;
                break;
            case 7:
                x = 2;
                y = 0;
                break;
            case 8:
                x = 2;
                y = 1;
                break;
            case 9:
                x = 2;
                y = 2;
                break;

        }
        return new XYReplication(x, y);
    }

    private static class XYReplication {
        int x, y;

        public XYReplication(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
