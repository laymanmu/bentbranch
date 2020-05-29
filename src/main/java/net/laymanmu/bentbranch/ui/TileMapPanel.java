package net.laymanmu.bentbranch.ui;
import net.laymanmu.bentbranch.tiles.TileMap;
import net.laymanmu.bentbranch.tiles.TileMapBuilder;

import javax.swing.*;
import java.awt.*;

public class TileMapPanel extends JPanel {
    private final int width;
    private final int height;
    private final TileMap tileMap;

    public TileMapPanel(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.tileMap = TileMapBuilder.buildRoom(Window.TileMapWidth, Window.TileMapHeight);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.fillOval(100, 100, 30, 30);
        */
        tileMap.draw(g);
    }
}

