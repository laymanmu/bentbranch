package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.tiles.TileMap;
import net.laymanmu.bentbranch.tiles.TileMapBuilder;
import net.laymanmu.bentbranch.tiles.TileType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TileMapTest {
    private int roomWidth;
    private int roomHeight;
    private TileMap room;

    @Before
    public void setup() {
        roomWidth = 10;
        roomHeight = 10;
        room = TileMapBuilder.buildRoom(roomWidth, roomHeight);
    }

    @Test
    public void testRoom() {
        System.out.println(room);
        for (int x=0; x<roomWidth; x++) {
            for (int y=0; y<roomHeight; y++) {
                var tile = room.getTile(x, y);
                var msg = "tile: "+ x +","+ y;
                if (x == 0 || y == 0  || x == roomWidth-1 || y == roomHeight-1) {
                    Assert.assertEquals(msg, TileType.Wall, tile.tileType);
                } else {
                    Assert.assertTrue(msg, tile.tileType == TileType.Floor || tile.tileType == TileType.Gate);
                }
            }
        }
    }

}
