package battleship;

public class BattleField {
    private char[][] field;
    private int size;

   BattleField(int size) {
       this.size = size;
       this.field = new char[size][size];
       initField();
   }

   private void initField() {
       for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {
               field[i][j] = '~';
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

    private boolean isAnyNeighbours(Position position) {
        for (int i = position.getMinRow() - 1; i <= position.getMaxRow() + 1; i++) {
            for (int j = position.getMinCol() - 1; j <= position.getMaxCol() + 1; j++) {
                if ( position.isCorrect(getSize()) && field[i][j] == 'O' ) {
                    return true;
                }
            }
        }

        return false;
    }

    void placeShips(ShipType ship, String start, String end) {
        Position position = new Position(start, end);

        if (!position.isCorrectLength(ship)) {
            throw new IllegalArgumentException("Error! Wrong length of the " + ship.toString() + "! Try again :");
        }

        if (position.isDiagonal()) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        }

        if (isAnyNeighbours(position)) {
            throw new IllegalArgumentException("Error! You placed it too close to another one. Try again:");
        }

        for (int i = position.getMinRow(); i <= position.getMaxRow(); i++) {
            for (int j = position.getMinCol(); j <= position.getMaxCol(); j++) {
                field[i][j] = 'O';
            }
        }
    }

    void print() {
        printCol();
        printRow();
    }

}
