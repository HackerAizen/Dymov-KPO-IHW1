package bar.hse.ru.reversy.description;

public class Couple {
    private final int x;
    private final int y;
    
    public int getX() {
        return x; // ok
    }
    
    public int getY() {
        return y;
    }
    
    public Couple(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }
}
