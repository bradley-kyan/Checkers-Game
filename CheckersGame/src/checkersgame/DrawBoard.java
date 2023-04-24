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
    
    public DrawBoard(int dimension)
    {
        super(dimension);
    }
    
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
    
    private void clearScreen()
    {
        for(int i = 0; i < 100; i++)
        {
            System.out.println();
        }
    }
    
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

