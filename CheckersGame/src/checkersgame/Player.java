package checkersgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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

    /**
     * This is a constructor method for the Player class that creates a new Player object.
     * The method checks if there is already a player in the system with the same name.
     * If there is, it retrieves that player's existing statistics and assigns them to the new player object. 
     * If not, the method initializes the new player with default statistics.
     * @param name The user name of the player
     * @param colour The colour the player is playing as.
     */
    public Player(String name, Colour colour) {
        if(playerList == null)
            getPlayers();
        
        Player tempPlayer = this.getPlayer(name);
        
        if(tempPlayer != null)
        {
            parsePlayer(tempPlayer);
            this.colour = colour;
            playerList.remove(tempPlayer);
            playerList.add(this);
        }
        else
        {
            this.name = name;
            this.losses = 0;
            this.wins = 0;
            this.score = 0;
            this.colour = colour;
            playerList.add(this);
        }
        
    }
   
    /**
     * Inititalises the player name
     * @param name The name of the player
     */
    private Player(String name)
    {
        this.name = name;
    }

    /**
     * Updates the information of an existing player.
     * @param p a new  player object
     */
    private void parsePlayer(Player p)
    {
        this.name = p.name;
        this.score = p.score;
        this.wins = p.wins;
        this.losses = p.losses;
    }

    /**
     * Reads player data from a  text file and creates player object based on that information.
     */
    public static void getPlayers() {
        if (playerList != null) {
            return; // don't load players if already loaded
        }
        playerList = new ArrayList<Player>();
        try (BufferedReader bReader = new BufferedReader(new FileReader("players.txt"))) {
            String line;
            while ((line = bReader.readLine())!= null) 
            {
                String[] fields = line.split(",");
                String name = fields[0];
                int score = Integer.parseInt(fields[1]);
                int wins = Integer.parseInt(fields[2]);
                int losses = Integer.parseInt(fields[3]);
                Player player = new Player(name);
                player.score = score;
                player.wins = wins;
                player.losses = losses;
                playerList.add(player);
            }
        } catch (FileNotFoundException ex) {
        }catch(IOException ex){
            
        }
    }

    /**
     * Iterates through an arraylist of players and checks for a specified player.
     * @param name the name  of the player that we want returned
     * @return player if player name is found else returns null if player name isnt found
     */
    public Player getPlayer(String name)
    {
        for (Player player : playerList) 
        {
            if (player.name.equals(name)) 
            {
                return player;
            }
        }
        
        return null;
    }

    /**
     * Updates the text file with any new data that is present within the array list 
     * which is not already in the text file
     */
    public static void updateFile()
    {       
        try (FileWriter fw = new FileWriter("players.txt", false)) 
        {
            String str = "";
            for (Player player : playerList) 
            {
                str += player.name + "," + player.score + "," + player.wins + "," + player.losses + "\n";
                //Player's name, score, wins, and losses to the file
            }
            
            fw.write(str);
            fw.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred while writing the players to the file: " + e.getMessage());
        }

    }
    
    /*
    *Generates a string which depicts a players wins losses and  total pieces captured
    * @return a string with the players wins losses and score.
    */
    public String getWinLossString()
    {
        return "Wins: " + this.wins + ", Losses: " + this.losses + " Total Captures: " + this.score;
    }
    
    public Colour getColour() {
        return this.colour;
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
    /*
    * Creates a leaderboard with all players  names  and data such as wins losses and score.
    */
    public static void displayLeaderboard() {
    if(playerList == null)
        getPlayers();
    
    Collections.sort(playerList, new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p2.getScore() - p1.getScore(); // sort by descending score
        }
    });
    
    System.out.println("Leaderboard:");
    System.out.println("-----------");
    for(int i = 0; i < Math.min(10, playerList.size()); i++) {
        Player player = playerList.get(i);
        System.out.println((i+1) + ". " + player.name +" "+player.getWinLossString());
    }
    }
}
