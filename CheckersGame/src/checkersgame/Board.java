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
    
    /**
     * Initializes a new checkers board, and populates each side's pieces. 
     * @param size Number of squares the board will have in both an x and y axis
     */
    public Board(int size)
    {
        this.dimension = size;
        populateBoard(size);
    }
    
    /**
     * Populates the board with the correct placement and number of pieces.
     * @param dimension Size of the board
     */
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
                if(y == dimension - 2)
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
    
    /**
     * 
     */
    public void updateMoves()
    {
        for(Piece p : pieces)
        {
            p.moves = this.getMoves(p.position);
        }
    }
    
    /**
     * Finds a piece that is on the board which has the input ID
     * @param ID ID of a piece
     * @return Piece object or null if no matching ID
     */
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
      
    /**
     * Checks piece list and gets the Piece that is in the same Point location.
     * @param point Board location
     * @return Piece object or null if no piece is found at specified location
     */
    public Piece getPiece(Point point)
    {
        for(Piece p : pieces)
        {
            if(p.position.equals(point))
                return p;
        }
        return null; 
    }

    /**
     * Gets the ArrayList of pieces.
     * @return ArrayList<Piece> of all alive pieces
     */
    public ArrayList<Piece> getPieces()
    {
        return this.pieces;
    }
    
    /**
     * Moves a specified piece to a new location. Location must be valid. Valid moves
     * are defined in piece's move list (LinkedPoint). If valid move, will update 
     * piece position and remove all pre-calculated to be removed pieces.
     * @param piece Piece to be moved
     * @param location New location of piece
     * @see LinkedPoint.java
     */
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
    
    /**
     * Filters out the potential moves from the inputted moves. Filtered moves
     * are moved which a piece can move to. It calculates moved based on if there
     * is a piece in its move location, or if it can capture the opponent's piece
     * @param directionalMoves Pre-calculated potential moves on the diagonal axis.
     * @param origin Piece which movements are for.
     * @return ArrayList<LinkedPoint> containing all valid moves a piece can move.
     * @see getMoves()
     */
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
    
    /**
     * Gets the total number of pieces a colour has on the board.
     * @param colour Colour to check
     * @return int of remaining pieces
     */
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
    
    /**
     * Gets all the moves which a piece can legally move to.
     * @param origin Piece which movements are for. 
     * @return ArrayList<LinkedPoint> containing moves a piece can move to.
     */
    private ArrayList<LinkedPoint> getMoves(Point origin)
    {
        return this.filterMoves(this.potentialMoves(origin), this.getPiece(origin));
    }
    
    /**
     * Gets all points that are diagonal to input point. 
     * @param p Point on the board
     * @return Multidimensional ArrayList of points in both negative, and positive
     * diagonal directions to given point.
     * @see getMoves()
     */
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
