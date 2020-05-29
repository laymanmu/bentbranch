package net.laymanmu.bentbranch.ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Resources {
    private final static ConcurrentHashMap<String,BufferedImage> imageCache = new ConcurrentHashMap<>();

    public static BufferedImage getImage(String fileName) {
        if (!imageCache.containsKey(fileName)) {
            try {
                var path = String.format("%d/%s", Window.TileSize, fileName);
                var image = ImageIO.read(new File(Objects.requireNonNull(Resources.class.getClassLoader().getResource(path)).getFile()));
                imageCache.put(fileName, image);
                System.out.println("loaded image from disk: "+ path);
            } catch (IOException e) {
                return null;
            }
        }
        return imageCache.get(fileName);
    }
}
