package net.laymanmu.bentbranch;

public class TileMap {
    private int width;
    private int height;
    private Tile[] tiles;
    private TileMapSymbols symbols;

    public TileMap(int width, int height, TileMapSymbols symbols) {
        this.width = width;
        this.height = height;
        this.symbols = symbols;
        this.tiles = new Tile[width*height];
    }

    public Tile getTile(int x, int y) {
        return this.tiles[y*this.width + x];
    }

    public void setTile(int x, int y, Tile tile) {
        this.tiles[y*this.width + x] = tile;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int y=0; y<this.height; y++) {
            for (int x=0; x<this.width; x++) {
                String tileSymbol = this.symbols.getSymbol(getTile(x,y).tileType);
                sb.append(tileSymbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
