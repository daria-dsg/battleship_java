package battleship;

public class Ship {
    private ShipType shipType;
    private PositionPair positionPair;
    private int hitTimes;

    Ship (ShipType shipType, PositionPair positionPair) {
        this.hitTimes = 0;
        this.shipType = shipType;
        this.positionPair = positionPair;
    }

    boolean isInPosition(int row, int col) {
        boolean isRow = row >= positionPair.getMinRow() && row <= positionPair.getMaxRow();
        boolean isCol = col >= positionPair.getMinCol() && col <= positionPair.getMaxCol();

        return isRow && isCol;
    }

    void hit() {
        hitTimes += 1;
    }

    boolean isSink() {
        return hitTimes >= shipType.getSize();
    }
}
