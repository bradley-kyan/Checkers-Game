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

    public static void getPlayers() {
        if (playerList != null) {
            return; // don't load players if already loaded
        }
        playerList = new ArrayList<>();
        try (BufferedReader bReader = new BufferedReader(new FileReader("players.txt"))) {
            String line;
            while ((line = bReader.readLine())!= null) {
               String[] fields = line.split(" "); // assuming tab-delimited fields
                String name = fields[0];
                Colour colour = Colour.valueOf(fields[1]);
                int score = Integer.parseInt(fields[2]);
                int wins = Integer.parseInt(fields[3]);
                int losses = Integer.parseInt(fields[4]);
                Player player = new Player(name, colour);
                player.score = score;
                player.wins = wins;
                player.losses = losses;
                playerList.add(player);
            }
        } catch (FileNotFoundException ex) {
        }catch(IOException ex){
            
        }
    }
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
    public static void updateFile()
    {
           
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("players.txt"))) 
        {
        for (Player player : playerList) {
            //Player's name, colour, score, wins, and losses to the file
            writer.write(player.name + "," + player.colour.toString() + "," + player.score + "," + player.wins + "," + player.losses + "\n");
        }
        } catch (IOException e) {
        System.out.println("An error occurred while writing the players to the file: " + e.getMessage());
        }

    }
    
    public String getWinLossString()
    {
    return "Wins: " + this.wins + ", Losses: " + this.losses;
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
