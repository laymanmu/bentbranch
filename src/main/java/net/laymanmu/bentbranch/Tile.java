package net.laymanmu.bentbranch;

public class Tile extends Token {
    public final TileType tileType;

    public Tile(int x, int y, TileType tileType) {
        super(x, y, tileType.symbol);
        this.tileType = tileType;
    }
}
