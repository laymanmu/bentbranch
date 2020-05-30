package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.world.Point;
import org.junit.Test;
import org.junit.Assert;

public class PointTest {

    @Test
    public void testDirectionHelpers() {
        var p = new Point(10,10, 0);

        // defaults:
        Assert.assertEquals("north()", "(10,9,0)", p.north().toString());
        Assert.assertEquals("northEast()", "(11,9,0)", p.northEast().toString());
        Assert.assertEquals("east()", "(11,10,0)", p.east().toString());
        Assert.assertEquals("southEast()", "(11,11,0)", p.southEast().toString());
        Assert.assertEquals("south()", "(10,11,0)", p.south().toString());
        Assert.assertEquals("southWest()", "(9,11,0)", p.southWest().toString());
        Assert.assertEquals("west()", "(9,10,0)", p.west().toString());
        Assert.assertEquals("northWest()", "(9,9,0)", p.northWest().toString());

        // params:
        Assert.assertEquals("north(3)", "(10,7,0)", p.north(3).toString());
        Assert.assertEquals("northEast(2)", "(12,8,0)", p.northEast(2).toString());
        Assert.assertEquals("east(4)", "(14,10,0)", p.east(4).toString());
        Assert.assertEquals("southEast(10)", "(20,20,0)", p.southEast(10).toString());
        Assert.assertEquals("south(2)", "(10,12,0)", p.south(2).toString());
        Assert.assertEquals("southWest(3)", "(7,13,0)", p.southWest(3).toString());
        Assert.assertEquals("west(4)", "(6,10,0)", p.west(4).toString());
        Assert.assertEquals("northWest(1)", "(9,9,0)", p.northWest().toString());

        // chains:
        Assert.assertEquals("northWest(3).southEast(3)", "(10,10,0)", p.northWest(3).southEast(3).toString());
    }

    @Test
    public void testToString() {
       var p = new Point(1,1, 0);
       Assert.assertEquals("(1,1,0)", p.toString());
       p = new Point(123, 433, 0);
       Assert.assertEquals("(123,433,0)", p.toString());
    }

    @Test
    public void testEquals() {
        var p1 = new Point(1,1, 0);
        var p2 = new Point(1,1, 0);
        Assert.assertTrue(p1.equals(p2));
        Assert.assertTrue(p2.equals(p1));
        p2 = new Point(1, 2, 0);
        Assert.assertFalse(p1.equals(p2));
        Assert.assertFalse(p2.equals(p1));
        p2 = new Point(2, 1, 0);
        Assert.assertFalse(p1.equals(p2));
        Assert.assertFalse(p2.equals(p1));
    }
}
