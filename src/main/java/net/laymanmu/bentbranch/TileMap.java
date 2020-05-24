package net.laymanmu.bentbranch;

import java.util.HashMap;

public class TileMap {
    public final Point size;
    private final Tile[] tiles;
    private final HashMap<String,Mob> mobs;

    public TileMap(int width, int height) {
        this.size = new Point(width,height);
        this.tiles = new Tile[width*height];
        this.mobs = new HashMap<>();
    }

    public void onCollision(Mob movingMob, Mob restingMob) {
        System.out.println(movingMob +" collided into "+ restingMob);
    }

    public boolean move(Mob mob, Point point) {
        mobs.put(mob.getId(), mob);

        if (getTile(point).isBlocked) {
            return false;
        }

        Mob restingMob = getMob(point);
        if (restingMob != null) {
            onCollision(mob, restingMob);
            return false;
        }

        mob.setPosition(point);
        return true;
    }

    public Mob getMob(Point point) {
        for (var mob : mobs.values()) {
            if (mob.getPosition().equals(point)) {
                return mob;
            }
        }
        return null;
    }

    public Tile getTile(int x, int y) {
        return this.tiles[x + y*size.x];
    }

    public Tile getTile(Point point) {
        return this.tiles[point.x + point.y*size.x];
    }

    public void setTile(Tile tile) {
        this.tiles[tile.point.x + tile.point.y*size.x] = tile;
    }

    // factories:

    public static TileMap Room(Point size) {
        var tileMap = new TileMap(size.x, size.y);
        for (var x=0; x<size.x; x++) {
            for (var y=0; y<size.y; y++) {
                boolean isBlocked = x==0||x==size.x-1||y==0||y==size.y-1;
                tileMap.setTile(new Tile(x, y, isBlocked));
            }
        }
        return tileMap;
    }

}
