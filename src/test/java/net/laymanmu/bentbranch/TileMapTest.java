package net.laymanmu.bentbranch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TileMapTest {
    private Point roomSize;
    private TileMap room;

    @Before
    public void setup() {
        roomSize = new Point(10,10);
        room = TileMap.Room(roomSize);
    }

    @Test
    public void testRoom() {
        for (int x=0; x<roomSize.x; x++) {
            for (int y=0; y<roomSize.y; y++) {
                boolean isBorder = x==0||x==roomSize.x-1||y==0||y==roomSize.y-1;
                var tile = room.getTile(x,y);
                Assert.assertEquals("blocked borders", isBorder, tile.isBlocked);
                Assert.assertEquals("x", x, tile.point.x);
                Assert.assertEquals("y", y, tile.point.y);
            }
        }
    }

    @Test
    public void testMoving() {
        Point blocked = new Point(0,0);
        Point unblocked = new Point(1,1);
        Mob mob = new Mob("mob");

        // blocked:
        Assert.assertTrue("is blocked", room.getTile(blocked).isBlocked);
        Assert.assertFalse("can't move", room.move(mob, blocked));

        // unblocked:
        Assert.assertFalse("is not blocked", room.getTile(unblocked).isBlocked);
        Assert.assertTrue("can move", room.move(mob, unblocked));
    }

    @Test
    public void testCollision() {
        Point point = new Point(1,1);
        Mob mob1 = new Mob("mob1");
        Mob mob2 = new Mob("mob2");
        Assert.assertTrue("start", room.move(mob1, point));
        Assert.assertFalse("collision", room.move(mob2, point));
        Assert.assertTrue("clear way", room.move(mob1, mob1.getPosition().south(3)));
        Assert.assertTrue("no collision", room.move(mob2, point));
    }

    @Test
    public void testUpdate() {
        Mob mob = new Mob("mob");
        Assert.assertTrue("move", room.move(mob, new Point(1,1)));
        Assert.assertEquals("health start", 100, mob.health());
        Assert.assertEquals("energy start", 100, mob.energy());

        mob.consume(Resource.ResourceName.Health, 50);
        mob.consume(Resource.ResourceName.Energy, 50);
        Assert.assertEquals("health consumed", 50, mob.health());
        Assert.assertEquals("energy consumed", 50, mob.energy());

        int healthDelta = 1;
        int energyDelta = 5;

        room.update();
        Assert.assertEquals("health update 1", 50+healthDelta, mob.health());
        Assert.assertEquals("energy update 1", 50+energyDelta, mob.energy());

        room.update();
        Assert.assertEquals("health update 2", 50+healthDelta*2, mob.health());
        Assert.assertEquals("energy update 2", 50+energyDelta*2, mob.energy());
    }
}

