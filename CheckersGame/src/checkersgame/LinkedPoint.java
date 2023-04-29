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
    
    public Point toMove;
    public Piece origin;
    public ArrayList<Point> toBeRemoved;
    
    /**
     * 
     */
    public LinkedPoint()
    {
        this.toBeRemoved = new ArrayList<Point>();
    }  
}
