package bar.hse.ru.reversy.mainfiles

import java.util.Scanner;

public class Menu {
    private final ChooseAction nowGame = new Game();
    
    private static Menu ourMenu;
    private Menu() {
    }
    public static Menu getOurMenu() {
        if (ourMenu == null) {
            ourMenu = new Menu();
        }
        return ourMenu;
    } // ok
    public void startMenu(){
        while(true) {
            System.out.println("Choose What to do");
            System.out.println("PvM - Human vs computer");
            System.out.println("PvwM - Human vs wise computer");
            System.out.println("PvP - 2 real players");
            System.out.println("SS - Show score");
            System.out.println("SMS - Show max score");
            System.out.println("finish - finish the program");
            Scanner in = new Scanner(System.in);
            switch (in.nextLine()) {
                case ("PvM") -> {
                    runPvM();
                }
                case ("PvwM") -> {
                    runPvwM();
                }
                case ("PvP") -> {
                    runPvP();
                }
                case ("SS") -> {
                    showScore();
                }
                case ("SMS") -> {
                    showMaxScore();
                }
                case ("finish") -> {
                    return;
                }
                default -> System.out.println("Wrong input, Try Again");
            }
        }
    }
}
