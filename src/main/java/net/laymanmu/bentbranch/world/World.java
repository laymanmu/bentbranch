package net.laymanmu.bentbranch.world;

import java.util.ArrayList;
import java.util.List;

import laymanmu.bentbranch.mob.Creature;

public class World {
	
	private Tile[][][] tiles;
	
	private List<Creature> creatures;
	public  List<Creature> creatures() { return creatures; }
	
	private int width;
	public  int width() { return width; }
	
	private int height;
	public  int height() { return height; }

	private int depth;
	public  int depth() { return depth; }

	public World(Tile[][][] tiles) {
		this.tiles  = tiles;
		this.width  = tiles.length;
		this.height = tiles[0].length;
		this.depth  = tiles[0][0].length;
		creatures = new ArrayList<Creature>();
	}
	
	public Tile tile(int x, int y, int z) {
		if (x<0 || x>=width || y<0 || y>=height || z<0 || z>depth) {
			return Tile.BOUNDS;
		} else {
			return tiles[x][y][z];
		}
	}
	
	public void dig(int x, int y, int z) {
		if (tile(x,y,z).isDiggable()) {
			tiles[x][y][z] = Tile.FLOOR;
		}
	}
	
	public void addAtEmptyLocation(Creature creature, int z) {
		int x = -1;
		int y = -1;
		
		while (isBlocked(x, y, z)) {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}
		creature.x = x;
		creature.y = y;
		creature.z = z;
		creatures.add(creature);
	}
	
	public Creature creatureAt(int x, int y, int z) {
		if (x<0 || x>=width || y<0 || y>=height || z<0 || z>depth) {
			return null;
		}
		for (Creature c : creatures) {
			if (c.x == x && c.y == y && c.z == z) {
				return c;
			}
		}
		return null;
	}

	public void remove(Creature creature) {
		creatures.remove(creature);
	}
	
	public void update() {
		Creature[] creatures2 = creatures.toArray(new Creature[creatures.size()]);
		for (Creature c : creatures2) {
			c.update();
		}
	}
	
	public boolean isBlocked(int x, int y, int z) {
		return tile(x,y,z).isBlocked() || creatureAt(x,y,z) != null;
	}

}
