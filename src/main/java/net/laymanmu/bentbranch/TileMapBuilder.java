package net.laymanmu.bentbranch;

public class TileMapBuilder {
    private TileMapBuilder() {}

    public static TileMap buildRoom(int width, int height) {
        TileMap tileMap = new TileMap(width, height, new TileMapSymbols());
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                TileType tileType = isBorder(width, height, x, y) ? TileType.Wall : TileType.Floor;
                tileMap.setTile(x, y, new Tile(x, y, tileType));
            }
        }
        return tileMap;
    }

    public static boolean isBorder(int width, int height, int x, int y) {
        return x==0 || x==width-1 || y==0 || y==height-1;
    }
}
