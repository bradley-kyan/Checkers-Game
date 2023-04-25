package checkersgame;

/**
 *
 * @author kishanyugendran
 */
public class Player {

    private Colour colour;
    private String name; //Name of the player
    private int score; //Total pice caputure history
    private int wins; //Total wins by player

    public Player(String name, Colour colour) {
        this.colour = colour;
        this.name = name;
        score = 0;
    }
    
    public Player(String name, Colour colour, int score, int wins)
    {
        this.name = name;
        this.colour = colour;
        this.score = score;
        this.wins = wins;
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
    
    public int getScore()
    {
        return this.score;
    }
    public int getWins()
    {
        return this.wins;
    }
}
