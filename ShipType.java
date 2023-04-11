package battleship;

public enum ShipType {
    AIRCRAFT(5), BATTLESHIP(4), SUBMARINE(3), CRUISER(3), DESTROYER(2);

    private int size;

    private ShipType(int cell) {
        this.size = cell;
    }

    @Override
    public String toString() {
        switch (this) {
            case AIRCRAFT:
                return "Aircraft Carrier";
            case BATTLESHIP:
                return "Battleship";
            case SUBMARINE:
                return "Submarine";
            case CRUISER:
                return "Cruiser";
            case DESTROYER:
                return "Destroyer";
            default:
                return null;
        }
    }

    int getSize() {
        return this.size;
    }
}
