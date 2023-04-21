package battleship;

import java.util.ArrayList;

public class Player {
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private String title;
    private Board board;

    Player (String title, int size) {
        this.title = title;
        this.board = new Board(size);
    }

    public void addShip(ShipType shipType, PositionPair positionPair) {
        ships.add(new Ship(shipType, positionPair));
    }
    public Board getBoard() {
        return this.board;
    }
    @Override
    public String toString() {
        return this.title;
    }

    public boolean isAllShipHit() {
        for (Ship ship : ships) {
            if (!ship.isSink()) { return false; }
        }
        return true;
    }

    public void hitShip(int i, int j) {
        for (Ship ship : ships) {
            PositionPair positionPair = ship.getPositionPair();
            if ( positionPair.isInclude(i, j) ) {
                ship.hit();
                System.out.println(ship.isSink() ? "You sank a ship!" : "You hit a ship!");
                break;
            }
        }
    }
}
