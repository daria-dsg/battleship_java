package battleship;

public class BattleField {
    private char[][] field;
    private int size;

    enum Sign {
        WATER('~'), SHIP('O'), HIT('X'), MISSED('M');
        char s;
        Sign (char s) {
            this.s = s;
        }

        char getSign() {
            return this.s;
        }
    }

   BattleField(int size) {
       this.size = size;
       this.field = new char[size][size];
       initWater();
   }

   private void initWater() {
       for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {
               setField(i, j, Sign.WATER.getSign());
           }
       }
   }

   private int getSize(){
        return this.size;
    }

   private void printCol() {
       System.out.print("  ");
       for (int i = 1; i <= size; i++) {
           System.out.print( i + " ");
       }

       System.out.println();
   }

   private void printRow() {
       for (int i = 0; i < size; i++) {
           System.out.print((char) (i + 65) + " ");
           for (int j = 0; j < size; j++) {
               System.out.print(field[i][j] + " ");
           }
           System.out.println();
       }
   }

    private boolean isPosValid(int i, int j) {return i >= 0 && j < size && j >= 0 && i < size;
    }

    private boolean isAnyNeighbours(PositionPair positionPair) {
        for (int i = positionPair.getMinRow() - 1; i <= positionPair.getMaxRow() + 1; i++) {
            for (int j = positionPair.getMinCol() - 1; j <= positionPair.getMaxCol() + 1; j++) {
                if ( !isPosValid(i, j)) { continue; }
                if ( isShip(i, j)) { return true; }
            }
        }
        return false;
    }

    private void setField(int i, int j, char sign ) {
        field[i][j] = sign;
    }

    private boolean isShip (int i, int j) {
        return field[i][j] == Sign.SHIP.getSign();
    }

    void place(ShipType ship, String start, String end) {
        PositionPair positionPair = new PositionPair(start, end);

        if (!positionPair.isCorrectLength(ship)) {
            throw new IllegalArgumentException("Error! Wrong length of the " + ship.toString() + "! Try again :");
        }

        if (positionPair.isDiagonal()) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        }

        if (isAnyNeighbours(positionPair)) {
            throw new IllegalArgumentException("Error! You placed it too close to another one. Try again:");
        }

        for (int i = positionPair.getMinRow(); i <= positionPair.getMaxRow(); i++) {
            for (int j = positionPair.getMinCol(); j <= positionPair.getMaxCol(); j++) {
                setField(i, j, Sign.SHIP.getSign());
            }
        }
    }

    void print() {
        printCol();
        printRow();
    }

    void hitShip(String posStr) {
       Position pos = new Position(posStr);
       int i = pos.getRow();
       int j = pos.getCol();
       if (!isPosValid(i, j)) {
           throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
       }
       if (isShip(i, j)) {
           setField(i, j, Sign.HIT.getSign());
           print();
           System.out.println("You hit a ship!");
       } else {
           setField(i, j, Sign.MISSED.getSign());
           print();
           System.out.println("You missed!");
       }
    }
}
