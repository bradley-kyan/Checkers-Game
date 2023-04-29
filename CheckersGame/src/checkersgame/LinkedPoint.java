/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author bradl
 */
public class LinkedPoint {
    
    public Point toMove; //Move location
    public Piece origin; //Piece which this move is for
    public ArrayList<Point> toBeRemoved; //Pieces to be removed
    
    /**
     * Initializes the object which will contain a Pieces valid moves, and all 
     * pieces that will be removed in the process.
     */
    public LinkedPoint()
    {
        this.toBeRemoved = new ArrayList<Point>();
    }  
}
