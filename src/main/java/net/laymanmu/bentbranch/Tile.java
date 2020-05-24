package net.laymanmu.bentbranch;

public class Tile {
    public final Point point;
    public final boolean isBlocked;

    public Tile(int x, int y, boolean tileIsBlocked) {
        isBlocked = tileIsBlocked;
        point = new Point(x,y);
    }
}
