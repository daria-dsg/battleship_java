package battleship;

public class Ship {
    private ShipType shipType;
    private PositionPair positionPair;
    private int hitTimes;

    Ship (ShipType shipType, PositionPair positionPair ) {
        this.hitTimes = 0;
        this.shipType = shipType;
        this.positionPair = positionPair;
    }

    public PositionPair getPositionPair() { return this.positionPair; }

    public void hit() {
        hitTimes += 1;
    }

    public boolean isSink() {
        return hitTimes >= shipType.getSize();
    }
}
