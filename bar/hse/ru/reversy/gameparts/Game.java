package bar.hse.ru.reversy.gameparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import bar.hse.ru.reversy.gameparts.FieldGame;
import bar.hse.ru.reversy.description.Gamer;
import bar.hse.ru.reversy.description.Player;
import bar.hse.ru.reversy.gameparts.ScoreBoard;

public class Game implements ChooseAction {
    private FieldGame gameField;
    private Gamer gamer1;
    private Gamer gamer2;
    private final ScoreBoard board;
    private final List<FieldGame> fieldInHistory;
    private int nowMove;

    public Game() {
        gameboard = new ScoreBoard();
        fieldInHistory = new ArrayList<>();
    }

    public void showScore() {
        this.board.showBoard();
    }

    public void showMaxScore() {
        this.board.showMaxBoard();
    }
    
    private boolean canBlackMove() {
        return ((gameField.blackCount() + gameField.whiteCount() < 64) && (gameField.blackCount() != 0) && (gameField.whiteCount() != 0) && (gameField.blackPossibleCount() != 0));
    }
    
    private boolean canWhiteMove() {
        return ((gameField.blackCount() + gameField.whiteCount() < 64) && (gameField.blackCount() != 0) && (gameField.whiteCount() != 0) && (gameField.whitePossibleCount() != 0));
    }

    private boolean canNobodyMove() {
        return ((gameField.blackPossibleCount() == 0) && (gameField.whitePossibleCount() == 0));
    }

    public void runPvM() {
        gameField = new FieldGame();
        fieldInHistory.add(gameField.copy());
        nowMove = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Gamer name:");
        String Name1 = in.nextLine();
        gamer1 = new Player(Name1);
        System.out.println("W - white cell");
        System.out.println("B - black cell");
        System.out.println("P - cell for possible move");
        System.out.println("O - empty cell");
        System.out.println("Position - a b");
        System.out.println("a - vertical coordinate");
        System.out.println("b - horizontal coordinate");
        System.out.println("If your move is out of the field, then you can cancel the previous move");
        while (true) {
            if (canWhiteMove() && (nowMove % 2 == 1)) {
                System.out.println("Your move");
                gameField.outFieldWhite();
                while (true) {
                    try {
                        System.out.println("Enter the position");
                        int curInA = in.nextInt();
                        int curInB = in.nextInt();
                        in.nextLine();
                        if (gameField.inField(curInA - 1, curInB - 1)) {
                            if (gameField.whiteMove(curInA, curInB)) {
                                ++nowMove;
                                fieldInHistory.add(gameField.copy());
                                break;
                            }
                        } else {
                            System.out.println("Wrong position");
                            System.out.println("Cancel the previous move?(yes/no)");
                            String answer = in.nextLine();
                            if (answer.equals("yes")) {
                                cancelMove(2);
                                break;
                            } else {
                                System.out.println("Enter the position again");
                            }
                        }
                    } catch(Exception e) {
                        System.out.println("Wrong position");
                        in.nextLine();
                    }
                }
            } else if (canNobodyMove()) {
                break;
            } else if (!canWhiteMove()){
                ++nowMove;
            }
            if (canBlackMove() && (nowMove % 2 == 0)) {
                gameField.blackComputerMove();
                fieldInHistory.add(gameField.copy());
                ++nowMove;
            } else if (canNobodyMove()) {
                break;
            } else if (!canBlackMove()){
                ++nowMove;
            }
        }
        gameField.outField();
        if (gameField.blackCount() > gameField.whiteCount()) {
            System.out.println("Computer wins");
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            System.out.printf("%s score: %d\n", gamer1.getName(), gameField.whiteCount());
        } else if (gameField.blackCount() < gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.gamer1.getName());
            System.out.printf("%s score: %d\n", gamer1.getName(), gameField.whiteCount());
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            this.gamer1.setScore(gameField.whiteCount());
            this.board.appendToBoard(this.gamer1);
        } else {
            System.out.println("Nobody");
        }
    }

    public void runPvwM() {
        gameField = new FieldGame();
        fieldInHistory.add(gameField.copy());
        nowMove = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Gamer name:");
        String Name1 = in.nextLine();
        gamer1 = new Player(Name1);
        System.out.println("W - white cell");
        System.out.println("B - black cell");
        System.out.println("P - cell for possible move");
        System.out.println("O - empty cell");
        System.out.println("Position - a b");
        System.out.println("a - vertical coordinate");
        System.out.println("b - horizontal coordinate");
        System.out.println("If your move is out of the field, then you can cancel the previous move");
        while (true) {
            if (canWhiteMove() && (nowMove % 2 == 1)) {
                System.out.println("Your move");
                gameField.outFieldWhite();
                while (true) {
                    try {
                        System.out.println("Enter the position");
                        int curInA = in.nextInt();
                        int curInB = in.nextInt();
                        in.nextLine();
                        if (gameField.inField(curInA - 1, curInB - 1)) {
                            if (gameField.whiteMove(curInA, curInB)) {
                                ++nowMove;
                                fieldInHistory.add(gameField.copy());
                                break;
                            }
                        } else {
                            System.out.println("Wrong position");
                            System.out.println("Cancel the previous move?(yes/no)");
                            String answer = in.nextLine();
                            if (answer.equals("yes")) {
                                cancelMove(2);
                                break;
                            } else {
                                System.out.println("Enter the position again");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Wrong position");
                        in.nextLine();
                    }
                }
            } else if (canNobodyMove()) {
                break;
            } else if (!canWhiteMove()) {
                ++nowMove;
            }
            if (canBlackMove() && (nowMove % 2 == 0)) {
                if (nowMove == 64) {
                    gameField.blackComputerMove();
                } else {
                    gameField.blackWiseMachineMove();
                }
                fieldInHistory.add(gameField.copy());
                ++nowMove;
            } else if (canNobodyMove()) {
                break;
            } else if (!canBlackMove()){
                ++nowMove;
            }
        }
        gameField.outField();
        if (gameField.blackCount() > gameField.whiteCount()) {
            System.out.println("Computer wins");
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            System.out.printf("%s score: %d\n", gamer1.getName(), gameField.whiteCount());
        } else if (gameField.blackCount() < gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.gamer1.getName());
            System.out.printf("%s score: %d\n", gamer1.getName(), gameField.whiteCount());
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            this.gamer1.setScore(gameField.whiteCount());
            this.board.appendToBoard(this.gamer1);
        } else {
            System.out.println("Nobody");
        }
    }

    public void runPvP() {
        gameField = new FieldGame();
        fieldInHistory.add(gameField.copy());
        nowMove = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("First gamer:");
        String Name1 = in.nextLine();
        System.out.println("Second gamer:");
        String Name2 = in.nextLine();
        gamer1 = new Player(Name1);
        gamer2 = new Player(Name2);
        System.out.println("W - white cell");
        System.out.println("B - black cell");
        System.out.println("P - cell for possible move");
        System.out.println("O - empty cell");
        System.out.println("Position - a b");
        System.out.println("a - vertical coordinate");
        System.out.println("b - horizontal coordinate");
        System.out.println("If your move is out of the field, then you can cancel the previous move");
        while (true) {
            if (canWhiteMove() && (nowMove % 2 == 1)) {
                System.out.println("White move");
                gameField.outFieldWhite();
                while (true) {
                    try {
                        System.out.println("Enter the position");
                        int curInA = in.nextInt();
                        int curInB = in.nextInt();
                        in.nextLine();
                        if (gameField.inField(curInA - 1, curInB - 1)) {
                            if (gameField.whiteMove(curInA, curInB)) {
                                ++nowMove;
                                fieldInHistory.add(gameField.copy());
                                break;
                            }
                        } else {
                            System.out.println("Wrong position");
                            System.out.println("Cancel the previous move?(yes/no)");
                            String answer = in.nextLine();
                            if (answer.equals("yes")) {
                                cancelMove(1);
                                break;
                            } else {
                                System.out.println("Enter the position again");
                            }
                        }
                    } catch(Exception e) {
                        System.out.println("Wrong position");
                        in.nextLine();
                    }
                }
            } else if (canNobodyMove()) {
                break;
            } else if (!canWhiteMove()){
                ++nowMove;
            }
            if (canBlackMove() && (nowMove % 2 == 0)) {
                System.out.println("Black move");
                gameField.outFieldBlack();
                while (true) {
                    try {
                        System.out.println("Enter the position");
                        int curInA = in.nextInt();
                        int curInB = in.nextInt();
                        in.nextLine();
                        if (gameField.inField(curInA - 1, curInB - 1)) {
                            if (gameField.blackMove(curInA, curInB)) {
                                ++nowMove;
                                fieldInHistory.add(gameField.copy());
                                break;
                            }
                        } else {
                            System.out.println("Wrong position");
                            System.out.println("Cancel the previous move?(yes/no)");
                            String answer = in.nextLine();

                            if (answer.equals("yes")) {
                                cancelMove(1);
                                break;
                            } else {
                                System.out.println("Enter the position again");
                            }
                        }
                    } catch(Exception e) {
                        System.out.println("Wrong position");
                        in.nextLine();
                    }
                }
            } else if (canNobodyMove()) {
                break;
            } else if (!canBlackMove()){
                ++nowMove;
            }
        }
        gameField.outField();
        if (gameField.blackCount() > gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.gamer2.getName());
            System.out.printf("%s score: %d\n", gamer2.getName(), gameField.blackCount());
            System.out.printf("%s score: %d\n", gamer1.getName(), gameField.whiteCount());
            this.gamer2.setScore(gameField.blackCount());
            this.board.appendToBoard(this.gamer2);
        } else if (gameField.blackCount() < gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.gamer1.getName());
            System.out.printf("%s score: %d\n", gamer1.getName(), gameField.whiteCount());
            System.out.printf("%s score: %d\n", gamer2.getName(), gameField.blackCount());
            this.gamer1.setScore(gameField.whiteCount());
            this.board.appendToBoard(this.gamer1);
        } else {
            System.out.println("Nobody");
        }
    }

    private void cancelMove(int num) {
        if (fieldInHistory.size() >= 2) {
            gameField = fieldInHistory.get(nowMove - 1 - num).copy();
            int newSize = fieldInHistory.size() - num;
            for (int i = fieldInHistory.size() - 1; i >= newSize; --i) {
                fieldInHistory.remove(i);
            }
            nowMove -= num;
        } else {
            System.out.println("Sorry, this is first move");
        }
    }
}
