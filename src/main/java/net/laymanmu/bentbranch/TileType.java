package net.laymanmu.bentbranch;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public enum TileType {
    None("?", "none.png", true),
    Floor(".", "floor.png", false),
    Wall("#", "wall.png", true),
    Gate(">", "gate.png", false);

    public final boolean isBlocked;
    public final String symbol;
    public final BufferedImage image;

    private TileType(String symbol, String imageFileName, boolean isBlocked) {
        this.symbol    = symbol;
        this.isBlocked = isBlocked;
        this.image = loadImage(imageFileName);
    }

    private BufferedImage loadImage(String fileName) {
        try {
            return ImageIO.read(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile()));
        } catch (IOException e) {
            return null;
        }
    }
}

