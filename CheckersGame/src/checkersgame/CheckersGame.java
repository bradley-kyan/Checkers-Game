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
        
        board.drawPieces();
    }
}
