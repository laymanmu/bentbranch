package net.laymanmu.bentbranch;

public enum TileType {
    None("?"),
    Floor("."),
    Wall("#"),
    Gate(">");

    public final String symbol;

    private TileType(String symbol) {
        this.symbol = symbol;
    }
}
