/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkersgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bradl
 */
public class CheckersGame {

    private static Player currentPlayer;
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
        //TODO ask the user to choose size of board default would be 8
        int size = scan.nextInt();
        //TODO add error checking to make only integers valid
        
        if(size % 2 != 0) 
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
