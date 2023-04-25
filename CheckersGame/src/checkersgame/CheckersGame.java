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
        System.out.println("Please enter red's name: ");
        String nameRed  = scan.nextLine();
        
        System.out.println("Please enter blacks's name: ");
        String nameBlack  = scan.nextLine();
        
        
        System.out.println(getTitle(nameRed, nameBlack));
        System.out.println("Press 'Enter' to start.");
        String anyKey  = scan.nextLine();
        int size  =  0;
        boolean input = false;
        
        while (!input) {
            System.out.println("Please enter an integer to set board size(Must be greater than 6): ");
            try {
                size = scan.nextInt();
                if(size > 6)
                {
                input = true;
                }
                else{
                    System.out.println("Invalid input! Please enter an integer greater than 6.");
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scan.next();
            }
        }

        if (size % 2 != 0)
            size++; //Makes sure that he size is an even number to prevent errors.
        
        board = new DrawBoard(size);
        Player playerRed = new Player(nameRed, Colour.RED);
        Player playerBlack = new Player(nameBlack, Colour.BLACK);
        
        while(board.remainingPieces(Colour.BLACK) > 0 || board.remainingPieces(Colour.RED) > 0)
        {
            while(!playTurn(playerRed));
            while(!playTurn(playerBlack));
        }
    }
    private static boolean playTurn(Player currentPlayer)
    {
        board.updateMoves();
        board.drawPieces(currentPlayer.getColour());
        
        Integer intInput   =  0;
        boolean input = false;
        
        while (!input) {
            try {
                intInput = scan.nextInt();
                input = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scan.next();
            }
        }

        if(board.getPiece(intInput) == null || board.getPiece(intInput).getColour() != currentPlayer.getColour())   
        {
            return false;
        }
        
        Piece[][] boardFrame = board.drawHint(currentPlayer.getColour(), intInput);      
        
        char charInput = scan.next().charAt(0);
                
        if(charInput == 'x' || charInput == 'X') //Player pressed x to go back to selection
            return false;
        
        boolean test = board.chooseHint(charInput, boardFrame, intInput);
        if(!test)
            return false;
        
        board.updateMoves();
        
        currentPlayer.colourSwitching();
        board.drawPieces(currentPlayer.getColour());
        scan.nextLine();
        
        return true;
    }
    private static String getTitle(String userName1, String userName2) {
        return (" ________  ___  ___  _______   ________  ___  __    _______   ________  ________      \n"
                + "|\\   ____\\|\\  \\|\\  \\|\\  ___ \\ |\\   ____\\|\\  \\|\\  \\ |\\  ___ \\ |\\   __  \\|\\   ____\\     \n"
                + "\\ \\  \\___|\\ \\  \\\\\\  \\ \\   __/|\\ \\  \\___|\\ \\  \\/  /|\\ \\   __/|\\ \\  \\|\\  \\ \\  \\___|_    \n"
                + " \\ \\  \\    \\ \\   __  \\ \\  \\_|/_\\ \\  \\    \\ \\   ___  \\ \\  \\_|/_\\ \\   _  _\\ \\_____  \\   \n"
                + "  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\\\  \\\\|____|\\  \\  \n"
                + "   \\ \\_______\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\ \\__\\\\ _\\ ____\\_\\  \\ \n"
                + "    \\|_______|\\|__|\\|__|\\|_______|\\|_______|\\|__| \\|__|\\|_______|\\|__|\\|__|\\_________\\\n"
                + "                                                                          \\|_________|\n"
                + "                                                                                      \n"
                + "\n"
                + "Welcome " + userName1 + " and " + userName2 + "!");
    }
}
