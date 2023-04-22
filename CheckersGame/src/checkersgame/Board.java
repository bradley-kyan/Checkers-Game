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
    protected int dimension;
    
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
                        new Point(x++, y)));
                }
                else
                {
                    if(x++ >= dimension)
                        continue;
                    
                    pieces.add(new Piece(Colour.RED, Rank.PAWN,
                        new Point(x, y)));
                }
            }
        }
        
        for(int y = dimension - 1; y > dimension - 4; y--)
        {
            for(int x = 0; x < dimension; x++)
            {
                if(y == 6)
                {
                    pieces.add(new Piece(Colour.BLACK, Rank.PAWN, 
                        new Point(x++, y)));
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
    
    public void updateMoves()
    {
        for(Piece p : pieces)
        {
            p.moves = this.getMoves(p.position);
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
        for(Piece p : pieces)
        {
            if(p.position.equals(point))
                return p;
        }
        return null; 
    }

    public ArrayList<Piece> getPieces()
    {
        return this.pieces;
    }
    
    public void movePiece(Piece piece, Point location)
    {
        ArrayList<LinkedPoint> points = piece.moves;
        
        for(LinkedPoint lp : points)
        {
            if(lp.toMove.equals(location))
            {
                for(Point p : lp.toBeRemoved)
                {
                    this.pieces.remove(this.getPiece(p));
                }
                piece.position.setLocation(lp.toMove);
            }
        }
        this.updateMoves();
    }
    
    public ArrayList<LinkedPoint> filterMoves(ArrayList<ArrayList<Point>> directionalMoves, Piece origin)
    {     
        ArrayList<LinkedPoint> filtered = new ArrayList<LinkedPoint>();
        
        for(ArrayList<Point> dimension : directionalMoves)
        {
            LinkedPoint lp = new LinkedPoint();
            Point lastPoint = null;
            
            for(Point p : dimension)
            {
                //There is no piece at the place we want to move to thus we can move
                if(this.getPiece(p) == null && this.getPiece(lastPoint) == null)
                {
                    lp.toMove = new Point(p);
                    lp.origin = origin;
                    filtered.add(lp);
                    break;
                }
                
                //There is a piece to jump
                if(this.getPiece(lastPoint) != null)
                {
                    //Can we move to the next square
                    if(this.getPiece(p) != null)
                    {
                        break; //We cannot move
                    }
                    else
                    {
                        //Check if the piece to be jumped is the same colour
                        if(this.getPiece(lastPoint).getColour().equals(origin.getColour()))
                        {
                            break;
                        }
                        
                        lp.toMove = new Point(p);
                        lp.toBeRemoved.add(lastPoint);
                        lp.origin = origin;

//                        //Coninue to check if you can do more jumps;
//                        ArrayList<LinkedPoint> nthFiltered = filterMoves(this.potentialMoves(p), origin);
//                        
//                        for(LinkedPoint i : nthFiltered)
//                        {
//                            //Add the previous removed pieces to the nth dimension
//                            i.toBeRemoved.add(lastPoint);
//                        }
                        
                        //Add these new positions to the moveset
                        filtered.add(lp);
//                        filtered.addAll(nthFiltered);
                        
                        break;
                    }          
                }
                lastPoint = p;
            }
        }    
        return filtered;
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
    
    private ArrayList<LinkedPoint> getMoves(Point origin)
    {
        return this.filterMoves(this.potentialMoves(origin), this.getPiece(origin));
    }
    
    private ArrayList<ArrayList<Point>> potentialMoves(Point p)
    {
        ArrayList<ArrayList<Point>> directionalMoves = new ArrayList<ArrayList<Point>>();
        
        ArrayList<Point> moves = new ArrayList<Point>();
        
        Point tempPos = new Point(p);
        
        while(++tempPos.x < dimension && tempPos.x >= 0)
        {
            if(++tempPos.y < dimension && tempPos.y >= 0)
            {
                moves.add(new Point(tempPos));
            }
        }        
        directionalMoves.add(moves);
        
        moves = new ArrayList<Point>();
        
        tempPos.setLocation(p);
        while(++tempPos.x < dimension && tempPos.x >= 0)
        {
            if(--tempPos.y < dimension && tempPos.y >= 0)
            {
                moves.add(new Point(tempPos));
            }
        }
        directionalMoves.add(moves);
        
        moves = new ArrayList<Point>();
        
        tempPos.setLocation(p);
        while(--tempPos.x < dimension && tempPos.x >= 0)
        {
            if(--tempPos.y < dimension && tempPos.y >= 0)
            {
                moves.add(new Point(tempPos));
            }
        }       
        directionalMoves.add(moves);
        
        moves = new ArrayList<Point>();
        
        tempPos.setLocation(p);
        while(--tempPos.x < dimension && tempPos.x >= 0)
        {
            if(++tempPos.y < dimension && tempPos.y >= 0)
            {
                moves.add(new Point(tempPos));
            }
        } 
        directionalMoves.add(moves);
                
        return directionalMoves;
    }
}
