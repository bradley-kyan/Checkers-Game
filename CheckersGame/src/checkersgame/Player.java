package checkersgame;

import java.util.ArrayList;

/**
 *
 * @author kishanyugendran
 */
public class Player {

    private Colour colour;
    private String name; //Name of the player
    private int score; //Total pice caputure history
    private int wins; //Total wins by player
    private int losses; //Total losses
    private static ArrayList<Player> playerList; //PlayerList should be shared across all player objects

    public Player(String name, Colour colour) {
        if(playerList == null)
            getPlayers();
        
        Player tempPlayer = this.getPlayer(name);
        if(tempPlayer != null)
        {
            parsePlayer(tempPlayer);
        }
        else
        {
            this.name = name;
            this.losses = 0;
            this.wins = 0;
            this.score = 0;
        }
        
        this.colour = colour;
    }
    
    private void parsePlayer(Player p)
    {
        this.name = p.name;
        this.colour = p.colour;
        this.score = p.score;
        this.wins = p.wins;
        this.losses = p.losses;
    }

    public static void getPlayers()
    {
        //TODO populate playerList with an array of Players from the data stored
        //in the file   -->  Use file i/o
    }
    public Player getPlayer(String name)
    {
        //TODO will get the player from the playerList,
        //if no player will return null, else will return the player from the list
        
        return null;
    }
    public static void updatePlayer(Player p)
    {
        //TODO will check against playerList to see if player name exists.
        //If exists will update the playerList with the new scores/wins/losses;
        //Else will add the player to the playerList
    }
    public static void updateFile()
    {
        //TODO method which will update the file with all the playerList contents
        //updateFile and getPlayers must read/write to the file in the same format
    }
    
    public Colour getColour() {
        return this.colour;
    }
    
    public void colourSwitching(){
        colour = (colour == Colour.RED) ? colour.BLACK : colour.RED;
    }
    
    public void capture()
    {
        this.score++;
    }
    public void win()
    {
        this.wins++;
    }
    public void lose()
    {
        this.losses++;
    }
    public int getScore()
    {
        return this.score;
    }
    public int getWins()
    {
        return this.wins;
    }
    public int getLosses()
    {
        return this.losses;
    }
}
