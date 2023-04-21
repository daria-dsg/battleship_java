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

    PositionPair (int startRow, int startCol, int endRow, int endCol) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;

        this.maxRow = Math.max(startRow, endRow);
        this.maxCol = Math.max(startCol, endCol);
        this.minRow = Math.min(startRow, endRow );
        this.minCol = Math.min(startCol, endCol);
    }

    public int getMaxRow() {
        return this.maxRow;
    }

    public int getMinRow() {
        return this.minRow;
    }

    public int getMaxCol() {
        return this.maxCol;
    }

    public int getMinCol() {
        return this.minCol;
    }

    public boolean isInclude(int row, int col) {
        return getMinRow() <= row && row <= getMaxRow() && getMinCol() <= col && col <= getMaxCol();
    }
}
