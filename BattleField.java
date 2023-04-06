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

   void print() {
       printCol();
       printRow();
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

    void placeShips(ShipType shipType, String start, String end) {
        Ship ship = new Ship(shipType, start, end);

        if (!ship.isCorrectLength()) {
            throw new IllegalArgumentException("Error! Wrong length of the " + shipType.toString() + "! Try again :");
        }

        for (int i = ship.getMinRow(); i <= ship.getMaxRow(); i++) {
            for (int j = ship.getMinCol(); j <= ship.getMaxCol(); j++) {
                field[i][j] = '0';
            }
        }
    }


}
