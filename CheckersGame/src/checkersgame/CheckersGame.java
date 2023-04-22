/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkersgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bradl
 */
public class CheckersGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DrawBoard board = new DrawBoard(8);       
        board.updateMoves();       
        board.drawPieces(Colour.RED);
        Piece[][] boardFrame = board.drawHint(Colour.RED, 10);
        
        board.chooseHint('b', boardFrame, 10);
        board.updateMoves();
        board.drawPieces(Colour.BLACK);
        
        Piece[][] boardFrame2 = board.drawHint(Colour.BLACK, 21);
        board.chooseHint('a', boardFrame2, 21);
        board.updateMoves();
        board.drawPieces(Colour.RED);
        
        board.drawPieces(Colour.BLACK);
        Piece[][] boardFrame3 = board.drawHint(Colour.BLACK, 22);
        board.chooseHint('a', boardFrame3, 22);
        board.updateMoves();
        
        board.drawPieces(Colour.RED);
        Piece[][] boardFrame4 = board.drawHint(Colour.RED, 10);
        board.chooseHint('a', boardFrame4, 10);
        board.updateMoves();
        
        board.updateMoves();
        board.drawPieces(Colour.BLACK);
        
        
    }
}
