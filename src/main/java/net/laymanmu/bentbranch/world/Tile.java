package net.laymanmu.bentbranch.world;

public enum Tile {
	FLOOR,
	WALL,
	BOUNDS,
	STAIRS_UP,
	STAIRS_DOWN;
	
	public boolean isDiggable() {
		return this == Tile.WALL;
	}
	
	public boolean isGround() {
		return this != Tile.WALL && this != Tile.BOUNDS;
	}
	
	public boolean isBlocked() {
		return !isGround();
	}
}
