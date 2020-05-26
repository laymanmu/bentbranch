package net.laymanmu.bentbranch;

public class Token {
    private int x;
    private int y;
    private String symbol;

    public Token(int x, int y, String symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSymbol() {
        return symbol;
    }
}
