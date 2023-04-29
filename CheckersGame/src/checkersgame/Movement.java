package checkersgame;

/**
 * @author kishanyugendran
 * @deprecated   
 */
public enum Movement {
    FORWARD( 1),
    BACK (-1),
    RIGHT (1),
    LEFT (-1);
    
    public int value;
    
    private Movement(int value)
    {
        this.value = value;
    }
}
