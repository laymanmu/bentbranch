package net.laymanmu.bentbranch.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Token {
    private int x;
    private int y;
    private final String symbol;
    private final BufferedImage image;

    public Token(int x, int y, String symbol, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.image = image;
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getSymbol() {
        return symbol;
    }

    public BufferedImage getImage() {
        return this.image;
    }
}

