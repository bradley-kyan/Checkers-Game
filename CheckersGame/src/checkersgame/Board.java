package checkersgame;

import java.awt.Point;

/**
 * @author bradl
 */
public class Board 
{    
    private Piece[][] pieces;
    
    public Board()
    {
        populateBoard(8);
    }
    
    private void populateBoard(int dimension)
    {
        this.pieces = new Piece[dimension][dimension];
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < dimension; x++)
            {
                if(y == 0 || y == 2)
                {
                    pieces[x++][y] = new Piece(Colour.RED, Rank.PAWN, 
                        new Point(x, y));
                }
                else
                {
                    if(x++ >= dimension)
                        continue;
                    
                    pieces[x][y] = new Piece(Colour.RED, Rank.PAWN,
                        new Point(x, y));
                }
            }
        }
        
        for(int y = dimension - 1; y > dimension - 4; y--)
        {
            for(int x = 0; x < dimension; x++)
            {
                if(y == 7 || y == 5)
                {
                    pieces[x++][y] = new Piece(Colour.BLACK, Rank.PAWN, 
                        new Point(x, y));
                }
                else
                {
                    if(x++ >= dimension)
                        continue;
                    
                    pieces[x][y] = new Piece(Colour.BLACK, Rank.PAWN,
                        new Point(x, y));
                }
            }
        }      
    }
    
    public Piece getPiece(int ID)
    {
        for(int x = 0; x < pieces.length; x++)
        {
            for(int y = 0; y < pieces[x].length; y++)
            {
                if(pieces[x][y] != null && pieces[x][y].getID() == ID)
                {
                    return pieces[x][y];
                }
            }
        }
        return null; 
    }

    public Piece[][] getPieces()
    {
        return this.pieces;
    }
    
    public void movePiece()
    {
        
    }
    public Boolean canJump()
    {
        
        return false;
    }
    
    public Boolean canMove()
    {
        return false;
    }
    
    public Boolean validMove(int ID, Movement y, Movement x)
    {
        Piece current = this.getPiece(ID);
        if(current == null)
            return false;
        if(current.position.x + x.value >= 0 && current.position.x < pieces.length)
        {
            if(current.position.y + y.value >= 0 && current.position.y < pieces.length)
            {
                if()
            }
        }      
        return false;
    }
    
    public void removePiece(int ID)
    {
        for(int x = 0; x < pieces.length; x++)
        {
            for(int y = 0; y < pieces[x].length; y++)
            {
                if(pieces[x][y] != null && pieces[x][y].getID() == ID)
                {
                    pieces[x][y] = null;
                }
            }
        }
    }
    
    public int remainingPieces(Colour colour)
    {
        int count = 0;
        
        for(int x = 0; x < pieces.length; x++)
        {
            for(int y = 0; y < pieces[x].length; y++)
            {
                if(pieces[x][y] != null && pieces[x][y].getColour() == colour)
                    count++;
            }
        }
        
        return count;
    }
}
