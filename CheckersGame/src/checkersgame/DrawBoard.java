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
    
    public void drawPieces()
    {
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

            for(String s : line)
            {
                System.out.println(s);
            }
        }
    }
    
    private void clearScreen()
    {
        for(int i = 0; i < 300; i++)
        {
            System.out.println();
        }
    }
    
    public String[] drawRed(int id, String[] lines)
    {
        if(id > 9)
        {
            lines[0] += "|-----------|";
            lines[1] += "|  _______  |";
            lines[2] += "| |  Red  | |";
            lines[3] += "| |   "+id+"  | |";
            lines[4] += "| |_______| |";  
            lines[5] += "|___________|";
        }
        else
        {
            lines[0] += "|-----------|";
            lines[1] += "|  _______  |";
            lines[2] += "| |  Red  | |";
            lines[3] += "| |   "+id+"   | |";
            lines[4] += "| |_______| |";  
            lines[5] += "|___________|"; 
        }
        
        return lines;
    }
    
    public String[] drawBlack(int id, String[] lines)
    {
        if(id > 9)
        {
            lines[0] += "|-----------|";
            lines[1] += "|  _______  |";
            lines[2] += "| | Black | |";
            lines[3] += "| |  "+id+"   | |";
            lines[4] += "| |_______| |";  
            lines[5] += "|___________|";
        }
        else
        {
            lines[0] += "|-----------|";
            lines[1] += "|  _______  |";
            lines[2] += "| | Black | |";
            lines[3] += "| |  "+id+"  | |";
            lines[4] += "| |_______| |";  
            lines[5] += "|___________|"; 
        }
        
        return lines;
    }
    
    private String[] drawSpace(String[] lines)
    {        
        lines[0] += "|-----------|";
        lines[1] += "|           |";
        lines[2] += "|           |";
        lines[3] += "|           |";
        lines[4] += "|           |";
        lines[5] += "|___________|"; 
        
        return lines;
    }
    
    private void drawTooltip(Piece piece)
    {
        
    }
}

