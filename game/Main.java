package game;

import static game.minimax.findBestMove;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter 1 for ai or 0 for human player");
        
          
        Scanner choose=new Scanner(System.in);
        String ai = choose.nextLine(); 
        if(ai.equals("1")){
        int board[][] = {{ 0, 0, 0}, 
                      { 0, 0, 0 }, 
                      { 0, 0, 0 }}; 
  
        

        
        while (game.hasWinner()==0){
            if(game.isGameOver())break;
            int turn = game.getPlayerTurn();
            if(turn==1){
                minimax.Move bestMove = findBestMove(game.getXoGame()); 
                System.out.println(game.insertMoveXY(bestMove.row, bestMove.col, 1));
            }else {
                System.out.print("o enter cell");
                int o = scanner.nextInt();
                System.out.println(game.insertMove(o,-1));
            }}
        }

        else{
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