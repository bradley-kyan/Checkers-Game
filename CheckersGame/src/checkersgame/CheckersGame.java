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
        
        board.updateMoves();
        System.out.println("");
        
        Piece p = board.getPiece(new Point(3,5));
                board.updateMoves();
        System.out.println(p.getID());
        ArrayList<LinkedPoint> moves = p.moves;
        System.out.println("--------------------------");
        System.out.println(p.position);
        System.out.println("--------------------------");
        for(LinkedPoint lp : moves)
        {
            System.out.println(lp.toMove);
            System.out.println(lp.toBeRemoved);
        }
        board.movePiece(p, moves.get(0).toMove);
        System.out.println("--------------------------");
        System.out.println(p.position);
        System.out.println("--------------------------");
        moves = p.moves;
        for(LinkedPoint lp : moves)
        {
            System.out.println(lp.toMove);
            System.out.println(lp.toBeRemoved);
        }
        
        board.movePiece(p, moves.get(0).toMove);
        
        System.out.println("--------------------------");
        System.out.println(p.position);
        System.out.println("--------------------------");

        moves = p.moves;
        for(LinkedPoint lp : moves)
        {
            System.out.println(lp.toMove);
            System.out.println(lp.toBeRemoved);
        }
        board.updateMoves();
        
        
        System.out.println("==========================");
        for(Piece poo : pieces)
        {
            System.out.println(poo.position + " : " + poo.getID() + " => " + poo.getRank() + " " + poo.getColour());
        }
        
    }
}
