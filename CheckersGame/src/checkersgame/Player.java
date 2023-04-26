package checkersgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        File file = new File("players.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\t"); // assuming tab-delimited fields
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
}
