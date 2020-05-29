package net.laymanmu.bentbranch.tiles;

import net.laymanmu.bentbranch.ui.Resources;

import java.awt.image.BufferedImage;

public enum TileType {
    None("?", "door_closed.png", true),
    Floor(".", "grass.png", false),
    Wall("#", "wall.png", true),
    Gate(">", "gate_open.png", false);

    public final boolean isBlocked;
    public final String symbol;
    public final BufferedImage image;

    private TileType(String symbol, String imageFileName, boolean isBlocked) {
        this.symbol    = symbol;
        this.isBlocked = isBlocked;
        this.image = Resources.getImage(imageFileName);
    }
}

