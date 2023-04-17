package checkersgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author bradl
 */
public class Board 
{    
    public ArrayList<Piece> pieces;
    private int dimension;
    
    public Board(int size)
    {
        this.dimension = size;
        populateBoard(size);
    }
    
    private void populateBoard(int dimension)
    {
        this.pieces = new ArrayList<Piece>();
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < dimension; x++)
            {
                if(y == 0 || y == 2)
                {
                    pieces.add(new Piece(Colour.RED, Rank.PAWN, 
                        new Point(x, y)));
                }
                else
                {
                    if(x++ >= dimension)
                        continue;
                    
                    pieces.add(new Piece(Colour.BLACK, Rank.PAWN,
                        new Point(x, y)));
                }
            }
        }
        
        for(int y = dimension - 1; y > dimension - 4; y--)
        {
            for(int x = 0; x < dimension; x++)
            {
                if(y == 7 || y == 5)
                {
                    pieces.add(new Piece(Colour.BLACK, Rank.PAWN, 
                        new Point(x, y)));
                }
                else
                {
                    if(x++ >= dimension)
                        continue;
                    
                    pieces.add(new Piece(Colour.BLACK, Rank.PAWN,
                        new Point(x, y)));
                }
            }
        }      
    }
    
    public Piece getPiece(int ID)
    {
        Iterator it = this.pieces.iterator();
        
        while(it.hasNext())
        {
            Piece p = (Piece) it.next();
            
            if(p.getID() == ID)
                return p;
        }
        return null; 
    }
        
    public Piece getPiece(Point point)
    {
        Iterator it = this.pieces.iterator();
        
        while(it.hasNext())
        {
            Piece p = (Piece) it.next();
            
            if(p.position.equals(point))
                return p;
        }
        return null; 
    }

    public ArrayList<Piece> getPieces()
    {
        return this.pieces;
    }
    
    public void movePiece()
    {
        
    }
    
    public Point canJump(Piece p)
    {   
        return null;
    }
    
    public Boolean canMove()
    {
        return false;
    }
    
    public Point validMove(int ID, Point p)
    {
        Piece current = this.getPiece(ID);

        if(p.x >= 0 && p.y < dimension)
        {
            for(Piece piece : pieces)
            {
                if(piece.getPos() == p)
                {
                    Point jumpPos = canJump(piece);
                    
                    if(jumpPos != null)
                        return jumpPos;
                    
                    return null;
                }               
                return p;
            }
        }      
        return null;
    }
    
    public ArrayList<Point> filterMoves(ArrayList<Point> rawMoves, Piece originPiece)
    {
        Point lastPoint = null;
        for(Point p : rawMoves)
        {
            for(Piece piece : pieces)
            {
                if(piece.position == p)
                {
                    if(lastPoint != null && Math.round(lastPoint.distance(p)) == 1)
                    {
                        
                    }
                }
            }
            
            lastPoint = p;
        }
        return rawMoves;
    }
    
    public void removePiece(int ID)
    {
        Iterator it = pieces.iterator();
        
        while(it.hasNext())
        {
            Piece p = (Piece)it.next();
            if(p.getID() == ID)
                it.remove();
        }
    }
    
    public int remainingPieces(Colour colour)
    {
        int count = 0;
        
        Iterator it = pieces.iterator();
        while(it.hasNext())
        {
            Piece p = (Piece)it.next();
            if(p.getColour() == colour)
                count++;
        }
        
        return count;
    }
    
    public ArrayList<Point> getMoves(int ID)
    {
        Piece piece = this.getPiece(ID);
        
        ArrayList<Point> moves = new ArrayList<Point>();
        
        Point tempPos = piece.position;

        while(++tempPos.x < dimension)
        {
            while(++tempPos.y < dimension)
            {
                moves.add(tempPos);
            }
        }
        while(++tempPos.x < dimension)
        {
            while(--tempPos.y < dimension)
            {
                moves.add(tempPos);
            }
        }
        while(--tempPos.x < dimension)
        {
            while(++tempPos.y < dimension)
            {
                moves.add(tempPos);
            }
        }
        while(--tempPos.x < dimension)
        {
            while(--tempPos.y < dimension)
            {
                moves.add(tempPos);
            }
        }   
        
        moves = this.filterMoves(moves, piece);
        
        return moves;
    }
}
