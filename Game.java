package battleship;

import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private static BattleField field = new BattleField(10);
    private static void placeShips() {
        for (ShipType ship : ShipType.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", ship.toString(), ship.getCell());

            while (true) {
                try {
                    String start = scanner.next().toUpperCase();
                    String end = scanner.next().toUpperCase();
                    field.place(ship, start, end);
                    break;
                } catch (Exception e)   {
                    System.out.println(e.getMessage());
                }
            }
            field.print();
        }
    }

    private void start() {
        System.out.println("The game starts!");
        field.print();
        takeTurn();
    }
    private void takeTurn() {
        System.out.println("Take a shot!");

        while (true) {
            try {
                field.hitShip(scanner.next());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    void play() {
        field.print();
        placeShips();
        start();
    }
}
