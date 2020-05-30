package net.laymanmu.bentbranch.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class Point {
	public final int x;
	public final int y;
	public final int z;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point point = (Point) o;
		if (x != point.x) return false;
		if (y != point.y) return false;
		return z == point.z;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 30 * result + y;
		result = 30 * result + z;
		return result;
	}

	@Override
	public String toString() {
		return String.format("(%d,%d,%d)", x, y, z);
	}

	public List<Point> neighbors8() {
		List<Point> points = new ArrayList<>();
		for (int ox = -1; ox < 2; ox++) {
			for (int oy = -1; oy < 2; oy++) {
				if (ox == 0 && oy == 0) {
					continue;
				}
				points.add(new Point(x + ox, y + oy, z));
			}
		}
		Collections.shuffle(points);
		return points;
	}

	public Point north(int amount) {
		return new Point(x, y - abs(amount), z);
	}

	public Point northEast(int amount) {
		return new Point(x + abs(amount), y - abs(amount), z);
	}

	public Point east(int amount) {
		return new Point(x + abs(amount), y, z);
	}

	public Point southEast(int amount) {
		return new Point(x + abs(amount), y + abs(amount), z);
	}

	public Point south(int amount) {
		return new Point(x, y + abs(amount), z);
	}

	public Point southWest(int amount) {
		return new Point(x - abs(amount), y + abs(amount), z);
	}

	public Point west(int amount) {
		return new Point(x - abs(amount), y, z);
	}

	public Point northWest(int amount) {
		return new Point(x - abs(amount), y - abs(amount), z);
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
