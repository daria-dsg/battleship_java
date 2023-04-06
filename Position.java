package battleship;

public class Position {
    int startRow;
    int startCol;
    int endRow;
    int endCol;
    int maxRow ;
    int maxCol;
    int minRow;
    int minCol;


    Position (String start, String end) {
        this.startRow = getRow(start);
        this.startCol = getCol(start);
        this.endRow = getRow(end);
        this.endCol = getCol(end);
        this.maxRow = Math.max(startRow, endRow);
        this.maxCol = Math.max(startCol, endCol);
        this.minRow = Math.min(startRow, endRow );
        this.minCol = Math.min(startCol, endCol);
    }

    private int getRow(String str) {
        return str.charAt(0) - 65;
    }

    private int getCol(String str) {
        return Integer.parseInt(String.valueOf(str.substring(1))) - 1;
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
        return maxRow - minRow == ship.getCell() - 1  || maxCol - minCol == ship.getCell() - 1;
    }

    boolean isDiagonal() {
        return maxRow != minRow && maxCol != minCol;
    }
}
