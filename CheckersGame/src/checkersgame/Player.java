package checkersgame;

/**
 *
 * @author kishanyugendran
 */
public class Player {

    private Colour colour;

    public Player(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour() {
        return this.colour;
    }
    
    public void colourSwitching(){
        colour = (colour == Colour.RED) ? colour.BLACK : colour.RED;
    }
}
