package net.laymanmu.bentbranch.tiles;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TileMapBuilder {
    private static final Random random = new Random();

    private TileMapBuilder() {}

    public static TileMap buildRoom(int width, int height) {
        TileMap tileMap = new TileMap(width, height);
        ArrayList<Point> floorPositions = new ArrayList<>();
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                TileType tileType = isBorder(width, height, x, y) ? TileType.Wall : TileType.Floor;
                tileMap.setTile(x, y, new Tile(x, y, tileType));
                if (tileType == TileType.Floor) {
                    floorPositions.add(new Point(x,y));
                }
            }
        }

        Point gatePosition = floorPositions.get(random.nextInt(floorPositions.size()));
        tileMap.setTile(gatePosition.x, gatePosition.y, new Tile(gatePosition.x, gatePosition.y, TileType.Gate));
        return tileMap;
    }

    public static boolean isBorder(int width, int height, int x, int y) {
        return x==0 || x==width-1 || y==0 || y==height-1;
    }
}
