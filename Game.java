package battleship;

import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private static BattleField field = new BattleField(10);
    private static void placeShips() {
        for (ShipType ship : ShipType.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", ship.toString(), ship.getSize());

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
            field.print(false);
        }
    }

    private void start() {
        System.out.println("The game starts!");
        field.print(true);
        System.out.println("Take a shot!");
    }
    private void takeTurn() {
        while (isNotOver()) {
            try {
                String position = scanner.next().toUpperCase();
                field.makeShot(position);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private boolean isNotOver() {
        return !field.isAllShipHit();
    }

    void play() {
        field.print(true);
        placeShips();
        start();
        takeTurn();
    }
}
