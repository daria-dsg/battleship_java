package battleship;

import java.util.ArrayList;

public class BattleField {
    private char[][] field;
    private int size;
    private ArrayList<Ship> ships = new ArrayList<Ship>();
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

    private char getPos(int i, int j){
        return field[i][j];
    }

   private void printCol() {
       System.out.print("  ");
       for (int i = 1; i <= size; i++) {
           System.out.print( i + " ");
       }

       System.out.println();
   }

   private void printRow(boolean isHidden) {
       for (int i = 0; i < size; i++) {
           System.out.print((char) (i + 65) + " ");
           for (int j = 0; j < size; j++) {
               if (isHidden && isShip(i,j)) {
                   System.out.print(Sign.WATER.getSign() + " ");
               } else {
                   System.out.print(getPos(i, j) + " ");
               }
           }
           System.out.println();
       }
   }

    private boolean isPosValid(int i, int j) { return i >= 0 && i < size && j >= 0 && j < size;
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
    private boolean isHit (int i, int j) {
        return field[i][j] == Sign.HIT.getSign();
    }

    private Ship getShipByPosition(int row, int col) {
        for (Ship ship : ships) {
            if (ship.isInPosition(row, col)) {return ship;}
        }

        return null;
    }


    void place(ShipType shipType, String start, String end) {
        PositionPair positionPair = new PositionPair(start, end);

        if (!positionPair.isCorrectLength(shipType)) {
            throw new IllegalArgumentException("Error! Wrong length of the " + shipType.toString() + "! Try again :");
        }

        if (positionPair.isDiagonal()) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        }

        if (isAnyNeighbours(positionPair)) {
            throw new IllegalArgumentException("Error! You placed it too close to another one. Try again:");
        }

        ships.add(new Ship(shipType, positionPair));


        for (int i = positionPair.getMinRow(); i <= positionPair.getMaxRow(); i++) {
            for (int j = positionPair.getMinCol(); j <= positionPair.getMaxCol(); j++) {
                setField(i, j, Sign.SHIP.getSign());
            }
        }
    }

    void print(boolean isHidden) {
        printCol();
        printRow(isHidden);
    }

    void makeShot(String posStr) {
       Position pos = new Position(posStr);
       int i = pos.getRow();
       int j = pos.getCol();

       if (!isPosValid(i, j)) {
           throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
       }


       if (isShip(i, j) || isHit(i, j)) {
           Ship ship = getShipByPosition(i, j);

           setField(i, j, Sign.HIT.getSign());
           ship.hit();
           print(true);
           if (isAllShipHit()) {return;}

           String str = ship.isSink() ? "You sank a ship! Specify a new target: " : "You hit a ship! Try again: ";
           System.out.println(str);
       } else {
           setField(i, j, Sign.MISSED.getSign());
           print(true);
           System.out.println("You missed! Try again: ");
       }
    }

    boolean isAllShipHit() {
        for (Ship ship : ships) {
            if (!ship.isSink()) {return false;}
        }

        return true;
    }
}
