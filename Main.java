package com.Game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner=new Scanner(System.in);

        while (game.hasWinner()==0){
            if(game.isGameOver())break;
            int turn = game.getPlayerTurn();
            if(turn==1){
                System.out.print("x enter cell");
                int x = scanner.nextInt();
                System.out.println(game.insertMove(x,1));
            }else {
                System.out.print("o enter cell");
                int o = scanner.nextInt();
                System.out.println(game.insertMove(o,-1));
            }
        }

        System.out.println(game.hasWinner());
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

}
