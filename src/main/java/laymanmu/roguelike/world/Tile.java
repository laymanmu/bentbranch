package laymanmu.roguelike.world;

import java.awt.Color;

public enum Tile {
	
	FLOOR((char)250, Color.yellow),
	WALL((char)177,  Color.yellow),
	BOUNDS('x',      Color.orange),
	STAIRS_UP('<',   Color.white),
	STAIRS_DOWN('>', Color.white);
	
	private char glyph;
	public char glyph() { return glyph; }
	
	private Color color;
	public Color color() { return color; }
	
	Tile(char glyph, Color color) {
		this.glyph = glyph;
		this.color = color;
	}
	
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
