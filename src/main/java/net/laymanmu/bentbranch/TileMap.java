package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.events.DeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TileMap {
    public final Point size;
    private final Tile[] tiles;
    private List<Mob> mobs;
    private final Dice dice;

    public TileMap(int width, int height) {
        this.size = new Point(width,height);
        this.tiles = new Tile[width*height];
        this.mobs = new ArrayList<>();
        this.dice = new Dice();
    }

    public void onCollision(Mob movingMob, Mob restingMob) {
        this.dice.attack(movingMob, restingMob).publish();
    }

    public boolean move(Mob mob, Point point) {
        if (!mobs.contains(mob)) {
            mobs.add(mob);
        }

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
        for (var mob : mobs) {
            if (mob.getPosition().equals(point)) {
                return mob;
            }
        }
        return null;
    }

    public int getMobCount() {
        return mobs.size();
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

    public void update() {
        var livingMobs = mobs.stream().filter(Mob::isAlive).collect(Collectors.toList());
        var deadMobs = mobs.stream().filter(mob -> !mob.isAlive());
        livingMobs.forEach(mob -> mob.update(this));
        deadMobs.forEach(mob -> new DeathEvent(mob.getName(), "the mob").publish());
        mobs = livingMobs;
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
