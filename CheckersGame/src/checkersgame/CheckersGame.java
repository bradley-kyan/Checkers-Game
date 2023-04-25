/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkersgame;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author bradl
 */
public class CheckersGame {

    private static DrawBoard board;
    private static Scanner scan;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        runGame();
    }
    
    public static void runGame()
    {
        int size  =  0;
        boolean input = false;
        
        while (!input) {
            System.out.println("Please enter an integer to set board size: ");
            try {
                size = scan.nextInt();
                input = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scan.next();
            }
        }

        if (size % 2 != 0)
            size++; //Makes sure that he size is an even number to prevent errors.
        
        board = new DrawBoard(size);
        Player player = new Player(Colour.RED);
        
        while(board.remainingPieces(Colour.BLACK) > 0 || board.remainingPieces(Colour.RED) > 0)
        {
            playTurn(player);
        }
    }
    
    private static void playTurn(Player currentPlayer)
    {
        board.updateMoves();
        board.drawPieces(currentPlayer.getColour());
        
        Integer intInput = scan.nextInt();
        //TODO add error checking for intInput
        
        
        if(board.getPiece(intInput).getColour() != currentPlayer.getColour())
            return;
        
        Piece[][] boardFrame = board.drawHint(currentPlayer.getColour(), intInput);      
        
        char charInput = scan.next().charAt(0);
                
        if(charInput == 'x' || charInput == 'X') //Player pressed x to go back to selection
            return;
        
        boolean test = board.chooseHint(charInput, boardFrame, intInput);
        if(!test)
            return;
        
        board.updateMoves();
        
        currentPlayer.colourSwitching();
        board.drawPieces(currentPlayer.getColour());
        scan.nextLine();
    }
    
    
    
}
