package checkersgame;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author bradl
 */
public class Piece {

    private Colour colour;
    private Rank rank;
    private Integer ID;
    public Point position;
    public int direction;
    private static Integer pieceNum;
    public ArrayList<LinkedPoint> moves;
    
    public Character hintIdentifier;
    
    /**
     * 
     * @param colour
     * @param rank
     * @param pos 
     */
    public Piece(Colour colour, Rank rank, Point pos)
    {
        this.colour = colour;
        this.rank = rank;
        
        if(colour == Colour.RED)
            direction = 1;
        else
            direction = -1;
        
        if(pieceNum == null)
            this.pieceNum = 0;
        this.ID = pieceNum++;
        
        this.position = pos;
    }
    
    public Piece(char c)
    {
        hintIdentifier = c;
    }
    
    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    public Point getPos()
    {
        return this.position;
    }
    
    public void setPos(Point pos)
    {
        this.position = pos;
    }   
}
