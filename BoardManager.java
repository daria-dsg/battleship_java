package battleship;

public class BoardManager {
    public static void checkPosPair (PositionPair pair, ShipType ship, Board board) {
        if (isNotCorrectLength(pair, ship)) {
            throw new IllegalArgumentException("Error! Wrong length of the " + ship.toString() + "! Try again :");
        }

        if (isDiagonal(pair)) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        }

        if (isAnyNeighbours(pair, board)) {
            throw new IllegalArgumentException("Error! You placed it too close to another one. Try again:");
        }
    }

    public static void checkPos (int i, int j, int size) {
        if (!isPosValid(i, j, size)) {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
        }
    }

    public  static void deployShipOnBoard (ShipType shipType,PositionPair positionPair,Player player, Board board) {
        for (int i = positionPair.getMinRow(); i <= positionPair.getMaxRow(); i++) {
            for (int j = positionPair.getMinCol(); j <= positionPair.getMaxCol(); j++) {
                board.placeShip(i, j);
            }
        }
    }

    private static boolean isPosValid(int i, int j, int size) { return i >= 0 && i < size && j >= 0 && j < size; }

    private static boolean isNotCorrectLength(PositionPair pair, ShipType ship) {
        return pair.getMaxRow() - pair.getMinRow() != (ship.getSize() - 1)  && pair.getMaxCol() - pair.getMinCol() != (ship.getSize() - 1);
    }

    private static boolean isDiagonal(PositionPair pair) {
        return pair.getMaxRow() != pair.getMinRow() && pair.getMaxCol() != pair.getMinCol();
    }

    private static boolean isAnyNeighbours(PositionPair positionPair, Board board) {
        for (int i = positionPair.getMinRow() - 1; i <= positionPair.getMaxRow() + 1; i++) {
            for (int j = positionPair.getMinCol() - 1; j <= positionPair.getMaxCol() + 1; j++) {
                if ( !isPosValid(i, j, board.getSize())) { continue; }
                if ( board.isShip(i, j)) { return true; }
            }
        }
        return false;
    }
}
