package battleship;

public class PositionPair {
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private int maxRow ;
    private int maxCol;
    private int minRow;
    private int minCol;

    PositionPair (String start, String end) {
        Position startPos = new Position(start);
        Position endPos = new Position(end);

        this.startRow = startPos.getRow();
        this.startCol = startPos.getCol();
        this.endRow = endPos.getRow();
        this.endCol = endPos.getCol();

        this.maxRow = Math.max(startRow, endRow);
        this.maxCol = Math.max(startCol, endCol);
        this.minRow = Math.min(startRow, endRow );
        this.minCol = Math.min(startCol, endCol);
    }


    int getMaxRow() {
        return this.maxRow;
    }

    int getMinRow() {
        return this.minRow;
    }

    int getMaxCol() {
        return this.maxCol;
    }

    int getMinCol() {
        return this.minCol;
    }

    boolean isCorrectLength(ShipType ship) {
        return maxRow - minRow == ship.getSize() - 1  || maxCol - minCol == ship.getSize() - 1;
    }

    boolean isDiagonal() {
        return maxRow != minRow && maxCol != minCol;
    }

}
