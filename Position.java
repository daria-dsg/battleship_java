package battleship;

public class Position {
    private int row;
    private int col;

    Position(String str) {
        this.row = toIntRow(str);
        this.col = toIntCol(str);
    }

    private int toIntRow(String str) {
        return str.charAt(0) - 65;
    }

    private int toIntCol(String str) { return Integer.parseInt(String.valueOf(str.substring(1))) - 1;}

    int getRow() {
        return this.row;
    }

    int getCol() {
        return this.col;
    }
}
