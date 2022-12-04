package bar.hse.ru.reversy.description;

public class Player implements Gamer {
    private final String name;
    private int score = 0;

    public Player(String Name) {
        name = Name;
    }

    public void setScore(int num) {
        score = num;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
