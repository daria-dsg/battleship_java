package battleship;

public class Board {
    private char[][] grid;
    private int size;

   Board (int size) {
       this.size = size;
       this.grid  = new char[size][size];
       init();
   }
    public int getSize(){
        return this.size;
    }

    public char getTile(int i, int j){
        return grid[i][j];
    }

    public boolean isShip (int i, int j) {
        return getTile(i, j) == Sign.SHIP.getSign();
    }

    public boolean isHit(int i, int j)  { return getTile(i, j) == Sign.HIT.getSign();}

    public void recordHit(int i, int j) {
        grid[i][j] = Sign.HIT.getSign();
    }

    public void recordMiss(int i, int j) { grid[i][j] = Sign.MISSED.getSign(); }

    public void placeShip(int i, int j ) { grid[i][j] = Sign.SHIP.getSign();}

    public void render() {
        printCol();
        printRow(true);
    }

    public void reveal() {
        printCol();
        printRow(false);
    }

    private final void init() {
       for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {
               initGrid(i, j, Sign.WATER);
           }
       }
   }

    private void initGrid(int i, int j, Sign sign) {
       grid[i][j] = Sign.WATER.getSign();
   }

    private void printCol() {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print( i + " ");
        }

        System.out.println();
    }

   private void printRow(boolean hidden) {
       for (int i = 0; i < size; i++) {
           System.out.print((char) (i + 65) + " ");
           for (int j = 0; j < size; j++) {
               if (hidden) {
                   System.out.print(Sign.WATER.getSign() + " ");
               } else {
                   System.out.print(getTile(i, j) + " ");
               }
           }
           System.out.println();
       }
   }
}
