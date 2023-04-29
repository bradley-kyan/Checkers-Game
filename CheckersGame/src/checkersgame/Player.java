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
     * 
     * @param name
     * @param colour 
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
     * 
     * @param name 
     */
    private Player(String name)
    {
        this.name = name;
    }
    
    /**
     * 
     * @param p 
     */
    private void parsePlayer(Player p)
    {
        this.name = p.name;
        this.score = p.score;
        this.wins = p.wins;
        this.losses = p.losses;
    }

    /**
     * 
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
     * 
     * @param name
     * @return 
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
     * 
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
