package bar.hse.ru.reversy.gameparts;

import bar.hse.ru.reversy.description.Cell;
import bar.hse.ru.reversy.description.Couple;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;

public class FieldGame {
    private final List<List<Cell>> gameField = new ArrayList<>();

    public FieldGame() {
        for (int i = 0; i < 8; ++i) {
            gameField.add(new ArrayList<>());
            for (int j = 0; j < 8; ++j) {
                gameField.get(i).add(new Cell());
            }
        }
        gameField.get(3).get(3).changeToWhiteColour();
        gameField.get(4).get(4).changeToWhiteColour();
        gameField.get(3).get(4).changeToBlackColour();
        gameField.get(4).get(3).changeToBlackColour();
    } // ok

    public FieldGame copy() {
        FieldGame result = new FieldGame();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                result.gameField.get(i).set(j, this.gameField.get(i).get(j).copy());
            }
        }
        return result;
    } // ok

    private boolean inCorner(int i, int j) {
        return (i == 0) && (j == 0) || (i == 0) && (j == 7) || (i == 7) && (j == 0) || (i == 7) && (j == 7);
    } // ok

    private boolean inOuterLine(int i, int j) {
        return ((i > 0) && (i < 7) && (j == 0 || j == 7)) || ((j > 0) && (j < 7) && (i == 0 || i == 7));
    } // ok

    public boolean inField(int i, int j) {
        return ((i >= 0) && (i <= 7)) && ((j >= 0) && (j <= 7));
    } // ok

    private Couple runner(char colour, int a, int b, int i, int j) {
        int aNow = a;
        int bNow = b;
        while ((this.inField(i + aNow, j + bNow))) {
            if ((gameField.get(i + aNow).get(j + bNow).defineColour() == colour)) {
                aNow += a;
                bNow += b;
            } else {
                break;
            }
        }
        return new Couple(aNow, bNow);
    } // ok

    public void outField() {
        System.out.println("  1_2_3_4_5_6_7_8 ");
        for (int i = 0; i < 8; ++i) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < 8; ++j) {
                System.out.printf("%c ", gameField.get(i).get(j).defineColour());
            }
            System.out.print("\n");
        }
    } // ok

    public void outFieldBlack() {
        List<Couple> possibleMoves = new ArrayList<>();
        System.out.println("  1_2_3_4_5_6_7_8 ");
        for (int i = 0; i < 8; ++i) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < 8; ++j) {
                if (gameField.get(i).get(j).defineColour() == 'O') {
                    char nowColour = gameField.get(i).get(j).defineColour();
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Couple now = runner('W', a, b, i, j);
                            int aNow = now.getX();
                            int bNow = now.getY();
                            if (this.inField(i + aNow, j + bNow)) {
                                if (wasChangedToBlack(i, j, a, b, aNow, bNow)) {
                                    nowColour = 'P';
                                }
                            }
                        }
                    }
                    System.out.printf("%c ", nowColour);
                    if (nowColour == 'P') {
                        possibleMoves.add(new Couple(i + 1, j + 1));
                    }
                } else {
                    System.out.printf("%c ", gameField.get(i).get(j).defineColour());
                }
            }
            System.out.print("\n");
        }
        System.out.println("Possible moves:");
        for (int i = 0; i < possibleMoves.size(); ++i) {
            System.out.printf("%d) %d %d\n", i + 1, possibleMoves.get(i).getX(), possibleMoves.get(i).getY());
        }
    } // ok
    
    private boolean wasChangedToBlack(int i, int j, int a, int b, int aNow, int bNow) {
        return ((gameField.get(i + aNow).get(j + bNow).defineColour() == 'B') && ((abs(aNow) > abs(a)) || (abs(bNow) > abs(b))));
    } // ok

    private boolean wasChangedToWhite(int i, int j, int a, int b, int aNow, int bNow) {
        return (gameField.get(i + aNow).get(j + bNow).defineColour() == 'W') && ((abs(aNow) > abs(a)) || (abs(bNow) > abs(b)));
    } // ok
    
    public void outFieldWhite() {
        List<Couple> possibleMoves = new ArrayList<>();
        System.out.println("  1_2_3_4_5_6_7_8 ");
        for (int i = 0; i < 8; ++i) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < 8; ++j) {
                if (gameField.get(i).get(j).defineColour() == 'O') {
                    char nowColour = gameField.get(i).get(j).defineColour();
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Couple now = runner('B', a, b, i, j);
                            int aNow = now.getX();
                            int bNow = now.getY();
                            if (this.inField(i + aNow, j + bNow)) {
                                if (wasChangedToWhite(i, j, a, b, aNow, bNow)) {
                                    nowColour = 'P';

                                }
                            }
                        }
                    }
                    System.out.printf("%c ", nowColour);
                    if (nowColour == 'P') {
                        possibleMoves.add(new Couple(i + 1, j + 1));
                    }
                } else {
                    System.out.printf("%c ", gameField.get(i).get(j).defineColour());
                }
            }
            System.out.print("\n");
        }
        System.out.println("Possible moves:");
        for (int i = 0; i < possibleMoves.size(); ++i) {
            System.out.printf("%d) %d %d\n", i + 1, possibleMoves.get(i).getX(), possibleMoves.get(i).getY());
        }
    } // ok

    public int blackCount() {
        int counter = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                if (gameField.get(i).get(j).defineColour() == 'B') {
                    ++counter;
                }
            }
        }
        return counter;
    } // ok

    public int whiteCount() {
        int counter = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                if (gameField.get(i).get(j).defineColour() == 'W') {
                    ++counter;
                }
            }
        }
        return counter;
    } // ok

    public int whitePossibleCount() {
        int counter = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                boolean flag = false;
                if (gameField.get(i).get(j).defineColour() == 'O') {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Couple now = runner('B', a, b, i, j);
                            int aNow = now.getX();
                            int bNow = now.getY();
                            if (this.inField(i + aNow, j + bNow)) {
                                if (wasChangedToWhite(i, j, a, b, aNow, bNow)) {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
                if (flag) {
                    ++counter;
                }
            }
        }
        return counter;
    } // ok

    public int blackPossibleCount() {
        int counter = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                boolean flag = false;
                if (gameField.get(i).get(j).defineColour() == 'O') {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Couple now = runner('W', a, b, i, j);
                            int aNow = now.getX();
                            int bNow = now.getY();
                            if (this.inField(i + aNow, j + bNow)) {
                                if (wasChangedToBlack(i, j, a, b, aNow, bNow)) {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
                if (flag) {
                    ++counter;
                }
            }
        }
        return counter;
    } // ok

    public boolean whiteMove(int i, int j) {
        --i;
        --j;
        boolean flag = false;
        if (inField(i, j)) {
            if (gameField.get(i).get(j).defineColour() == 'O') {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        Couple now = runner('B', a, b, i, j);
                        int aNow = now.getX();
                        int bNow = now.getY();
                        if (this.inField(i + aNow, j + bNow)) {
                            if (wasChangedToWhite(i, j, a, b, aNow, bNow)) {
                                flag = true;
                                int n = i;
                                int m = j;
                                for (int x = 0; x < max(abs(bNow), abs(aNow)); ++x) {
                                    gameField.get(n).get(m).changeToWhiteColour();
                                    n += a;
                                    m += b;
                                }
                            }
                        }
                    }
                }
            }
            if (!flag) {
                System.out.println("Wrong position");
            }
        } else {
            System.out.println("Wrong position");
        }
        return flag;
    } // ok

    public boolean blackMove(int i, int j) {
        --i;
        --j;
        boolean flag = false;
        if ((i >= 0) && (i <= 7) && (j >= 0) && (j <= 7)) {
            if (gameField.get(i).get(j).defineColour() == 'O') {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        Couple now = runner('W', a, b, i, j);
                        int aNow = now.getX();
                        int bNow = now.getY();
                        if (this.inField(i + aNow, j + bNow)) {
                            if (wasChangedToBlack(i, j, a, b, aNow, bNow)) {
                                flag = true;
                                int n = i;
                                int m = j;
                                for (int x = 0; x < max(abs(bNow), abs(aNow)); ++x) {
                                    gameField.get(n).get(m).changeToBlackColour();
                                    n += a;
                                    m += b;
                                }
                            }
                        }
                    }
                }
            }
            if (!flag) {
                System.out.println("Wrong position");
            }
        } else {
            System.out.println("Wrong position");
        }
        return flag;
    } // ok

    public void blackComputerMove() {
        int maxScore = 0, maxi = 0, maxj = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                int nowScore;
                if (this.inCorner(i, j)) {
                    nowScore = 8;
                } else if (this.inOuterLine(i, j)) {
                    nowScore = 4;
                } else {
                    nowScore = 0;
                }
                if (gameField.get(i).get(j).defineColour() == 'O') {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Couple now = runner('W', a, b, i, j);
                            int aNow = now.getX(), bNow = now.getY();
                            if (this.inField(i + aNow, j + bNow)) {
                                if (wasChangedToBlack(i, j, a, b, aNow, bNow)) {
                                    int n = i, m = j;
                                    for (int x = 0; x < max(abs(bNow), abs(aNow)); ++x) {
                                        if (inOuterLine(n, m)) {
                                            nowScore += 20;
                                        } else {
                                            nowScore += 10;
                                        }
                                        n += a;
                                        m += b;
                                    }
                                }
                            }
                        }
                    }
                }
                if (nowScore > maxScore) {
                    maxScore = nowScore;
                    maxi = i;
                    maxj = j;
                }
            }
        }
        blackMove(maxi + 1, maxj + 1);
    } // ok

    public int whiteComputerCount() {
        int maxScore = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                int nowScore;
                if (this.inCorner(i, j)) {
                    nowScore = 8;
                } else if (this.inOuterLine(i, j)) {
                    nowScore = 4;
                } else {
                    nowScore = 0;
                }
                if (gameField.get(i).get(j).defineColour() == 'O') {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Couple now = runner('B', a, b, i, j);
                            int aNow = now.getX(), bNow = now.getY();
                            if (this.inField(i + aNow, j + bNow)) {
                                if (wasChangedToWhite(i, j, a, b, aNow, bNow)) {
                                    int n = i, m = j;
                                    for (int x = 0; x < max(abs(bNow), abs(aNow)); ++x) {
                                        if (inOuterLine(n, m)) {
                                            nowScore += 20;
                                        } else {
                                            nowScore += 10;
                                        }
                                        n += a;
                                        m += b;
                                    }
                                }
                            }
                        }
                    }
                }
                if (nowScore > maxScore) {
                    maxScore = nowScore;
                }
            }
        }
        return maxScore;
    } // ok

    public void blackWiseMachineMove() {
        int maxScore = -64, maxi = 0, maxj = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                boolean flag = false;
                int nowScore;
                if (this.inCorner(i, j)) {
                    nowScore = 8;
                } else if (this.inOuterLine(i, j)) {
                    nowScore = 4;
                } else {
                    nowScore = 0;
                }
                if (gameField.get(i).get(j).defineColour() == 'O') {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Couple now = runner('W', a, b, i, j);
                            int aNow = now.getX(), bNow = now.getY();
                            if (this.inField(i + aNow, j + bNow)) {
                                if (wasChangedToBlack(i, j, a, b, aNow, bNow)) {
                                    int n = i, m = j;
                                    flag = true;
                                    for (int x = 0; x < max(abs(bNow), abs(aNow)); ++x) {
                                        if (inOuterLine(n, m)) {
                                            nowScore += 20;
                                        } else {
                                            nowScore += 10;
                                        }
                                        n += a;
                                        m += b;
                                    }
                                }
                            }
                        }
                    }
                }
                if (flag) {
                    FieldGame nowField = this.copy();
                    nowField.blackMove(i + 1, j + 1);
                    nowScore -= nowField.whiteComputerCount();
                    if (nowScore > maxScore) {
                        maxScore = nowScore;
                        maxi = i;
                        maxj = j;
                    }
                }
            }
        }
        blackMove(maxi + 1, maxj + 1);
    } // ok 
} // ok
