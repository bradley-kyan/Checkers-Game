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
public class DrawBoard extends Board{
    
    /**
     * Initializes a new checkers board, and populates each side's pieces. 
     * @param size Number of squares the board will have in both an x and y axis
     * @see Board.java
     */
    public DrawBoard(int dimension)
    {
        super(dimension);
    }
    
    /**
     * Draws the all the alive pieces to the screen line by line
     * @param currentTurn The colour of the current player's turn
     * @return Multidimensional Piece[][] array with all the alive pieces which
     * mimics the board's actual dimensions. AKA the board's frame
     */
    public Piece[][] drawPieces(Colour currentTurn)
    {
        this.clearScreen();
        
        ArrayList<Piece> pieces = super.pieces;
        
        Piece[][] boardFrame = new Piece[super.dimension][super.dimension];
       
        for(Piece p : pieces)
        {
            try
            { 
                Point location = p.position;
                boardFrame[location.x][location.y] = p;
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("There was an error...\n--------------------\n" + e);
            }          
        }
        
        for (int y = boardFrame.length - 1; y >= 0; y--)
        {
            String[] line = {"","","","","",""};

            for(int x = 0; x < boardFrame.length; x++)
            {
                if(boardFrame[x][y] == null)
                    line = this.drawSpace(line);
                else if(boardFrame[x][y].getColour() == Colour.RED)
                    line = this.drawRed(boardFrame[x][y].getID(), line);
                else
                    line = this.drawBlack(boardFrame[x][y].getID(), line);
            }

            if(y == 3)
            {
                line[1] += "  Red Pieces Remaining: " + super.remainingPieces(Colour.RED);
                line[2] += "  Black Pieces Remaining: " + super.remainingPieces(Colour.BLACK);
                line[4] += "  Current Side to Move: " + currentTurn;
            }
            
            for(String s : line)
            {
                System.out.println(s);
            }
        }  
        
        System.out.println("\nChoose " + currentTurn + " Piece to move (Enter number of piece): ");
        return boardFrame;
    }
    
    /**
     * Draws to the screen a selected piece's valid moves.
     * @param currentTurn The colour of the current player's turn
     * @param ID ID of the piece to show moves
     * @return Multidimensional Piece[][] array with all the alive pieces which
     * mimics the board's actual dimensions. AKA the board's frame
     */
    public Piece[][] drawHint(Colour currentTurn, int ID)
    {
        this.clearScreen();
        ArrayList<Piece> pieces = super.pieces;
        
        Piece[][] boardFrame = new Piece[super.dimension][super.dimension];
       
        for(Piece p : pieces)
        {
            char hintIdentifier = 'a';
        
            try
            { 
                Point location = p.position;
                boardFrame[location.x][location.y] = p;
                if(p.getID() == ID)
                {
                    for(LinkedPoint lp : p.moves)
                    {
                        boardFrame[lp.toMove.x][lp.toMove.y] = new Piece(hintIdentifier++);
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("There was an error...\n--------------------\n" + e);
            }
            
        }
        
        for (int y = boardFrame.length - 1; y >= 0; y--)
        {
            String[] line = {"","","","","",""};

            for(int x = 0; x < boardFrame.length; x++)
            {
                if(boardFrame[x][y] == null)
                    line = this.drawSpace(line);
                else if(boardFrame[x][y].hintIdentifier != null)
                    line = this.drawHint(boardFrame[x][y].hintIdentifier, line);
                else if(boardFrame[x][y].getColour() == Colour.RED)
                    line = this.drawRed(boardFrame[x][y].getID(), line);
                else
                    line = this.drawBlack(boardFrame[x][y].getID(), line);
            }

            if(y == 3)
            {
                line[1] += "  Red Pieces Remaining: " + super.remainingPieces(Colour.RED);
                line[2] += "  Black Pieces Remaining: " + super.remainingPieces(Colour.BLACK);
                line[4] += "  Current Side to Move: " + currentTurn;
            }
            
            for(String s : line)
            {
                System.out.println(s);
            }
        }  
        
        System.out.println("\nChoose where " + currentTurn + " Piece #" + ID +  " will move (Enter character of move, Press x to go back to selection): ");
        return boardFrame;
    }
    
    /**
     * Clear's the user's screen by printing a bunch of lines. Mimics a console 
     * clear command.
     */
    private void clearScreen()
    {
        for(int i = 0; i < 100 + super.dimension; i++)
        {
            System.out.println();
        }
    }
    
    /**
     * Selects a piece's valid move to move to.
     * @param c Move hint character
     * @param boardFrame Piece[][] array of all the board's pieces
     * @param ID ID of the piece to be moved
     * @return Boolean - True if valid character is inputted, and move was successful. 
     * False if piece was not moved.
     */
    public boolean chooseHint(Character c, Piece[][] boardFrame, int ID)
    {
        for (int y = boardFrame.length - 1; y >= 0; y--)
        {
            for(int x = 0; x < boardFrame.length; x++)
            {
                if(boardFrame[x][y] == null)
                    continue;
                
                if(boardFrame[x][y].hintIdentifier != null)
                {
                    if(boardFrame[x][y].hintIdentifier.equals(c))
                    {
                        Point move = new Point(x,y);
                        super.movePiece(super.getPiece(ID), move);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Generates a red piece. Adds it to the buffer of lines for a row.
     * @param id The ID of the piece for selection
     * @param lines String[] array of the current row buffer.
     * @return String[] of row buffer.
     */
    public String[] drawRed(int id, String[] lines)
    {
        if(id > 9)
        {
            lines[0] += " ------------- ";
            lines[1] += "|   _______   |";
            lines[2] += "|  |  Red  |  |";
            lines[3] += "|  |   "+id+"  |  |";
            lines[4] += "|  |_______|  |";  
            lines[5] += "|_____________|";
        }
        else
        {
            lines[0] += " ------------- ";
            lines[1] += "|   _______   |";
            lines[2] += "|  |  Red  |  |";
            lines[3] += "|  |   "+id+"   |  |";
            lines[4] += "|  |_______|  |";  
            lines[5] += "|_____________|"; 
        }
        
        return lines;
    }
    
    /**
     * Generates a black piece. Adds it to the buffer of lines for a row.
     * @param id The ID of the piece for selection
     * @param lines String[] array of the current row buffer.
     * @return String[] of row buffer.
     */
    public String[] drawBlack(int id, String[] lines)
    {
        if(id > 9)
        {
            lines[0] += " ------------- ";
            lines[1] += "|   _______   |";
            lines[2] += "|  | Black |  |";
            lines[3] += "|  |  "+id+"   |  |";
            lines[4] += "|  |_______|  |";  
            lines[5] += "|_____________|";
        }
        else
        {
            lines[0] += " ------------- ";
            lines[1] += "|   _______   |";
            lines[2] += "|  | Black |  |";
            lines[3] += "|  |  "+id+"  |  |";
            lines[4] += "|  |_______|  |";  
            lines[5] += "|_____________|"; 
        }
        
        return lines;
    }
    
    /**
     * Generates an empty space. Adds it to the buffer of lines for a row.
     * @param lines String[] array of the current row buffer.
     * @return String[] of row buffer.
     */
    private String[] drawSpace(String[] lines)
    {        
        lines[0] += " ------------- ";
        lines[1] += "|             |";
        lines[2] += "|             |";
        lines[3] += "|             |";
        lines[4] += "|             |";
        lines[5] += "|_____________|"; 
        
        return lines;
    }
    
    /**
     * Generates a hint selection for a piece. Adds it to the buffer of lines for a row.
     * @param selection Char of the character to be the identifier for the move
     * @param lines String[] array of the current row buffer.
     * @return String[] of row buffer.
     */
    private String[] drawHint(char selection, String[] lines)
    {        
        lines[0] += " ------------- ";
        lines[1] += "|             |";
        lines[2] += "|     ---     |";
        lines[3] += "|    ( "+selection+" )    |";
        lines[4] += "|     ---     |";
        lines[5] += "|_____________|"; 
        
        return lines;
    }
}

