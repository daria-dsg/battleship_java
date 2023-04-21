package battleship;

enum Sign {
    WATER('~'), SHIP('O'), HIT('X'), MISSED('M');
    private final char s;
    Sign (char s) {
        this.s = s;
    }
    char getSign() {
        return this.s;
    }
}