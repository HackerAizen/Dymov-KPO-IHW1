package bar.hse.ru.reversy.gameparts;

import bar.hse.ru.reversy.description.Gamer;
import java.util.List;
import java.util.ArrayList;

public class ScoreBoard {
    private final List<Gamer> players = new ArrayList<>();
    public void appendToBoard(Gamer comp) {
        players.add(comp);
    }

    public void showBoard() {
        for (int i = 0; i < players.size(); ++i) {
            System.out.printf("%s %d\n", this.players.get(i).getName(), this.players.get(i).getScore());
        }
        System.out.println();
    }

    public void showMaxBoard() {
        int scoreMax = 0;
        for (int i = 0; i  < players.size(); ++i) {
             if (scoreMax < this.players.get(i).getScore()) {
                 scoreMax = this.players.get(i).getScore();
             }
        }
        for (int i = 0; i  < players.size(); ++i) {
             if (scoreMax == this.players.get(i).getScore()) {
                 System.out.printf("%s %d\n", this.players.get(i).getName(), this.players.get(i).getScore());
             }
        }
        System.out.println();
    }
}
