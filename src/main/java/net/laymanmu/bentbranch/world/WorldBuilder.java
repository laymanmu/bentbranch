package net.laymanmu.bentbranch.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorldBuilder {
	private int width;
	private int height;
	private int depth;
	private Tile[][][] tiles;
	private int[][][] regions;
	private int nextRegion;
	
	public WorldBuilder(int width, int height, int depth) {
		this.width  = width;
		this.height = height;
		this.depth  = depth;
		this.tiles  = new Tile[width][height][depth];
		this.regions = new int[width][height][depth];
		this.nextRegion = 1;
	}
	
	public World build() {
		return new World(tiles);
	}
	
	private WorldBuilder randomizeTiles() {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				for (int z=0; z<depth; z++) {
					tiles[x][y][z] = Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL;
				}
			}
		}
		return this;
	}
	
	private WorldBuilder smooth(int times) {
		Tile[][][] tiles2 = new Tile[width][height][depth];
		for (int time=0; time<times; time++) {
			for (int z=0; z<depth; z++) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						// count neighbors:
						int floors = 0;
						int walls = 0;
						for (int nx = -1; nx < 2; nx++) {
							for (int ny = -1; ny < 2; ny++) {
								if (x + nx < 0 || x + nx >= width || y + ny < 0 || y + ny >= height) {
									continue;
								}
								if (tiles[x + nx][y + ny][z] == Tile.FLOOR) {
									floors++;
								} else {
									walls++;
								}
							}
						}
						// set like neighbors:
						tiles2[x][y][z] = floors >= walls ? Tile.FLOOR : Tile.WALL;
					}
				}
			}
			tiles = tiles2;
		}
		return this;
	}
	
	public WorldBuilder makeCaves() {
		return randomizeTiles().smooth(8).createRegions().connectRegions();
	}

	private WorldBuilder createRegions() {
		regions = new int[width][height][depth];
		for (int z=0; z<depth; z++) {
			for (int x=0; x<width; x++) {
				for (int y=0; y<height; y++) {
					if (tiles[x][y][z] != Tile.WALL && regions[x][y][z] == 0) {
						int size = fillRegion(nextRegion++, x, y, z);
						if (size < 25) {
							removeRegion(nextRegion-1, z);
						}
					}
				}
			}
		}
		return this;
	}

	private void removeRegion(int region, int z) {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (regions[x][y][z] == region) {
					regions[x][y][z] = 0;
					tiles[x][y][z] = Tile.WALL;
				}
			}
		}
	}

	private int fillRegion(int region, int x, int y, int z) {
		int size = 1;
		List<Point> open = new ArrayList<>();
		open.add(new Point(x,y,z));
		regions[x][y][z] = region;
		while (!open.isEmpty()) {
			Point p = open.remove(0);
			for (Point n : p.neighbors8()) {
				if (n.x<0 || n.x>=width || n.y<0 || n.y>=height) {
					continue;
				}
				if (regions[n.x][n.y][n.z] > 0 || tiles[n.x][n.y][n.z] == Tile.WALL) {
					continue;
				}
				size++;
				regions[n.x][n.y][n.z] = region;
				open.add(n);
			}
		}
		return size;
	}

	public WorldBuilder connectRegions() {
		for (int z=0; z<depth-1; z++) {
			connectRegionsDown(z);
		}
		return this;
	}

	public void connectRegionsDown(int z) {
		List<String> connected = new ArrayList<>();
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				String region = regions[x][y][z] +","+ regions[x][y][z+1];
				if (tiles[x][y][z] == Tile.FLOOR && tiles[x][y][z+1] == Tile.FLOOR && !connected.contains(region)) {
					connected.add(region);
					connectRegionsDown(z, regions[x][y][z], regions[x][y][z+1]);
				}
			}
		}
	}

	public void connectRegionsDown(int z, int r1, int r2) {
		List<Point> candidates = findRegionOverlaps(z, r1, r2);
		int stairs = 0;
		do {
			Point p = candidates.remove(0);
			tiles[p.x][p.y][z] = Tile.STAIRS_DOWN;
			tiles[p.x][p.y][z + 1] = Tile.STAIRS_UP;
			stairs++;
		} while (candidates.size() / stairs > 500);
	}

	public List<Point> findRegionOverlaps(int z, int r1, int r2) {
		List<Point> candidates = new ArrayList<>();
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (tiles[x][y][z] == Tile.FLOOR && tiles[x][y][z+1] == Tile.FLOOR) {
					if (regions[x][y][z] == r1 && regions[x][y][z+1] == r2) {
						candidates.add(new Point(x, y, z));
					}
				}
			}
		}
		Collections.shuffle(candidates);
		return candidates;
	}
}
