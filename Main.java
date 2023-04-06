package battleship;
import java.net.CookieHandler;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static BattleField field = new BattleField(10);

    public static void main(String[] args) {
        // Write your code here
        field.print();
        promptCoordinates();
    }

    public static void promptCoordinates() {

        for (ShipType ship : ShipType.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", ship.toString(), ship.getCell());

            while (true) {
                try {
                    String start = scanner.next().toUpperCase();
                    String end = scanner.next().toUpperCase();
                    field.placeShips(ship, start, end);
                    break;
                } catch (Exception e)   {
                    System.out.println(e.getMessage());
                }
            }
            field.print();
        }
    }
}
