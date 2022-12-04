package bar.hse.ru.reversy.description;

public class Cell {
    private String cellColour = "void"; // ok
    public Cell() {
    }
    
    public void changeToBlackColour() {
        cellColour = "black";
    }
    
    public void changeToWhiteColour() {
        cellColour = "white";
    }
    
    public char defineColour() {
        return switch (cellColour) {
            case ("void") -> 'O';
            case ("black") -> 'B';
            case ("white") -> 'W';
            default -> ' ';
        };
    }
    
    public Cell copy() {
        switch (cellColour) {
            case ("void") -> {
                return new Cell();
            }
            case ("white") -> {
                Cell result = new Cell();
                result.changeToWhiteColour();
                return result;
            }
            case ("black") -> {
                Cell result = new Cell();
                result.changeToBlackColour();
                return result;
            }
        }
        return null;
    }
}
