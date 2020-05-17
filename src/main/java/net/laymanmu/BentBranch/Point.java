package net.laymanmu.BentBranch;

import static java.lang.Math.abs;

public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            var other = (Point)obj;
            return other.x==x && other.y==y;
        }
        return super.equals(obj);
    }

    public Point north(int amount) {
        return new Point(x, y - abs(amount));
    }

    public Point northEast(int amount) {
        return new Point(x + abs(amount), y - abs(amount));
    }

    public Point east(int amount) {
        return new Point(x + abs(amount), y);
    }

    public Point southEast(int amount) {
        return new Point(x + abs(amount), y + abs(amount));
    }

    public Point south(int amount) {
        return new Point(x, y + abs(amount));
    }

    public Point southWest(int amount) {
        return new Point(x - abs(amount), y + abs(amount));
    }

    public Point west(int amount) {
        return new Point(x - abs(amount), y);
    }

    public Point northWest(int amount) {
        return new Point(x-abs(amount), y-abs(amount));
    }

    public Point north() {
        return north(1);
    }

    public Point northEast() {
        return northEast(1);
    }

    public Point east() {
        return east(1);
    }

    public Point southEast() {
        return southEast(1);
    }

    public Point south() {
        return south(1);
    }

    public Point southWest() {
        return southWest(1);
    }

    public Point west() {
        return west(1);
    }

    public Point northWest() {
        return northWest(1);
    }
}
