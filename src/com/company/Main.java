package com.company;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        initialiseField();
        Hexgame.doMain();
        /* scan = new Scanner(System.in);
        if(startingPositionIsGivenAsTextInput){
            String blueStart = scan.nextLine();
            String goldStart = scan.nextLine();
            String whoStarts = scan.nextLine();
        } else {

        }
        while(numTurns > 0){

            numTurns--;
        }*/
    }
    private static Integer from = null;
    private static PlayerType p1 = PlayerType.HUMAN;
    static PlayerType p2 = PlayerType.COMPUTER;
    private static final int numberOfMarkers = 4;
    private static int playerToMakeMove;
    protected static BoardPiece[] board;
    protected static BoardPiece currentTurn = BoardPiece.BLUE;
    private static Scanner scan;
    private static Integer numTurns = 200;
    //more then 110 numbers here. I didn't feel like counting.
    private static boolean  startingPositionIsGivenAsTextInput = false;

   
    private static void initialiseField() {
        board = new BoardPiece[110];
        for(int i = 0; i < board.length; i++){
            board[i] = BoardPiece.EMPTY;
        }
        board[0] = BoardPiece.GOLD;
        board[9] = BoardPiece.GOLD;
        board[103] = BoardPiece.GOLD;
        board[106] = BoardPiece.GOLD;
        board[100] = BoardPiece.BLUE;
        board[109] = BoardPiece.BLUE;
        board[3] = BoardPiece.BLUE;
        board[6] = BoardPiece.BLUE;

    }

    /*
     * METHODS BELOW ARE USED FOR READING THE INPUT TO INITIALISE THE GAME.
     */

    private static void readInput() throws IOException {
        // BufferedReader bufferedReader = new BufferedReader(new FileInputStream("turns.txt"));
        scan = new Scanner(new File("turns.txt"));
        BoardPiece turn = BoardPiece.BLUE;
        for(int i = 0; i < numTurns * 2; i++) {
            Integer from = scan.nextInt();
            Integer to = scan.nextInt();
            takeTurn(turn, from, to);
            turn = (turn == BoardPiece.BLUE)? BoardPiece.GOLD: BoardPiece.BLUE;
        }
    }



    private static boolean takeTurn(BoardPiece turn, Integer from, Integer to) {
        if(board[from] == turn) {
            if(board[to] == BoardPiece.EMPTY){
                if(withinRange(from, to)){
                    board[from] = BoardPiece.EMPTY;
                    board[to] = turn;
                    return true;
                }
            }
        }
        return false;

    }

    @SuppressWarnings("static-access")
    public static Computer click(Integer integer) {
        if(from == null ){
            if(board[integer] == currentTurn){
                from = integer;
            }
        } else if (from == integer){
            from = null; //double click the same tyle to deselect
        }else if (board[integer] == BoardPiece.EMPTY) {
            if(withinRange(from, integer)){
                board[integer] = board[from];
                board[from] = BoardPiece.EMPTY;
                from = null;
                currentTurn = (currentTurn == BoardPiece.BLUE)? BoardPiece.GOLD: BoardPiece.BLUE;
                numTurns--;

                return computerGenerator();
            } else {
                from = null;
            }
        }
        return null;

    }
    private static Computer computerGenerator() {
        // TODO Auto-generated method stub
        return new Gideon(board,numTurns,BoardPiece.GOLD, BoardPiece.BLUE );
    }
    private void printInput(){
        ArrayList<Integer> gold = new ArrayList<Integer>(),  blue = new ArrayList<Integer>();
        for(int i = 0; i <= 109; i++){
            if(board[i] == BoardPiece.GOLD){
                gold.add(i);
            } else if (board[i] == BoardPiece.BLUE){
                blue.add(i);
            }

        }
        for(Integer each: blue){
            System.out.print(each + " ");
        }
        System.out.println();
        for(Integer each: gold){
            System.out.print(each + " ");
        }
        System.out.println();
        if(currentTurn == BoardPiece.BLUE){
            System.out.print("0");
        } else {
            System.out.print("1");
        }

    }
    private static boolean withinRange(Integer from, Integer to) {
        if(to == from + 1 || to == from + 2 || to == from - 1 || to == from - 2 ) {
            if(to/10 == from /10)
                return true;
        } else if (to == from + 10 || to == from +20||to == from - 10 || to == from -20) {
            return true;
        } else if (to == from + 9 || to == from + 18||to == from - 9 || to == from - 18) {
            return true;
        }  else {
            return false;
        }
        return false;
    }

    private static void readPlayerPositions(BufferedReader bufferedReader) throws IOException {
        String line;
        int counter = 0;
        // There are two players, so need to read two lines of input of the position of the players positions.
        while (counter < 2) {
            while (true) {
                line = bufferedReader.readLine();
                // The line.replace(...) can possibly be removed if we are sure that input is not given with more than
                // one space in between the positions.
                line = line.replace(" ", ",");
                StringTokenizer st = new StringTokenizer(line, ",");
                if (st.countTokens() == numberOfMarkers) {
                    while (st.hasMoreTokens()) {
                        /*
                        Insert code to store the position in the appropriate data structure.
                         */
                    }
                    break;
                }
            }
        }
    }



}