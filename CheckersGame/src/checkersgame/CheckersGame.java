/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkersgame;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author bradl
 */
public class CheckersGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board(8);
        ArrayList<Piece> pieces = board.getPieces();
        for(Piece p : pieces)
        {
            System.out.println(p.position + " : " + p.getID() + " => " + p.getRank() + " " + p.getColour());
        }
        
        System.out.println("");
        System.out.println(board.getMoves(new Point(2,0)));
    }
}
