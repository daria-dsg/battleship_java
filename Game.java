package battleship;

import java.util.Scanner;
import static battleship.BoardManager.*;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private final int boardSize;
    private Player player1;
    private Player player2;

    Game (int boardSize) {
        this.boardSize = boardSize;
        this.player1 = new Player("Player 1", boardSize);
        this.player2 = new Player("Player 2", boardSize);
    }

    public void play() {
        deployAllShips(player1);
        deployAllShips(player2);

        while (true) {
            takeTurn (player1, player2);
            if (isWon()) {break;}
            passMove();

            takeTurn(player2, player1);
            if (isWon()) {break;}
            passMove();
        }

        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private void passMove() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        scanner.nextLine();
    }

//    deploy all ships
    private void deployAllShips(Player player) {
        System.out.println(player.toString() + ", place your ships on the game field");
        player.getBoard().render();

        for (ShipType ship : ShipType.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", ship.toString(), ship.getSize());
            deploy(ship,player);
            player.getBoard().reveal();
        }

        passMove();
    }

//   deploy one ship
    private void deploy(ShipType ship, Player player) {
        while (true) {
            String startPos = scanner.next().toUpperCase();
            String endPos = scanner.next().toUpperCase();

            int startRow = toIntRow(startPos);
            int startCol = toIntCol(startPos);
            int endRow = toIntRow(endPos);
            int endCol = toIntCol(endPos);

            PositionPair positionPair = new PositionPair(startRow, startCol, endRow, endCol);

            try {
                checkPosPair(positionPair, ship, player.getBoard());
                player.addShip(ship, positionPair);
                deployShipOnBoard(ship, positionPair, player, player.getBoard());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private  void takeTurn(Player player, Player enemy) {
        while (true) {
            player.getBoard().render();
            System.out.println("-".repeat(2 * boardSize + 1));
            player.getBoard().reveal();

            System.out.println(player.toString() + ", it's your turn:");
            String position = scanner.next().toUpperCase();
            int row = toIntRow(position);
            int col = toIntCol(position);

            try {
                checkPos(row, col, boardSize);
                callShot(row, col, enemy);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void callShot(int row, int col, Player enemy ) {
        Board enemyBoard = enemy.getBoard();
        if (enemyBoard.isShip(row, col) || enemyBoard.isHit(row,col)) {
            enemyBoard.recordHit(row, col);
            enemy.hitShip(row, col);
        } else {
            enemyBoard.recordMiss(row, col);
            System.out.println("You missed!");
        }
    }

    private boolean isWon() {
        return player1.isAllShipHit() || player2.isAllShipHit();
    }

    private int toIntRow(String str) {
        return str.charAt(0) - 65;
    }
    private int toIntCol(String str) { return Integer.parseInt(str.substring(1)) - 1;}

}
