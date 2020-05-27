package net.laymanmu.bentbranch;

import java.awt.*;

public class TileMap {
    private final int width;
    private final int height;
    private final Tile[] tiles;

    public TileMap(int width, int height) {
        this.width  = width;
        this.height = height;
        this.tiles  = new Tile[width*height];
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
                sb.append(getTile(x,y).tileType.symbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void draw(Graphics g) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                try {
                    var tile = getTile(x, y);
                    var image = tile.tileType.image;
                    if (image == null) {
                        System.out.println("image null for tileType: " + tile.tileType);
                    } else {
                        g.drawImage(image, x * Window.TileSize, y * Window.TileSize, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
