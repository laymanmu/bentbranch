package net.laymanmu.bentbranch.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame implements KeyListener, FocusListener, MouseListener {
    public static final String WindowTitle = "BentBranch";
    public static final int TileSize = 32;
    public static final int TileMapWidth = 20;
    public static final int TileMapHeight = 12;
    public static final int WindowWidth = TileSize * TileMapWidth + 12;
    public static final int WindowHeight = TileSize * TileMapHeight + 36;

    public static void main(String[] args) {
        Window window = new Window(WindowTitle, WindowWidth, WindowHeight);
        window.setVisible(true);
    }

    private final Map<Integer, String> keyMap;
    private final Map<String, Boolean> keyState;

    public Window(String title, int width, int height) {
        keyMap = new HashMap<>();
        keyState = new HashMap<>();
        addKeyListener(this);
        addFocusListener(this);
        addMouseListener(this);

        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setResizable(false);

        add(new TileMapPanel(width, height));
        pack();
    }

    public boolean isKeyPressed(String keyName) {
        if (keyState.containsKey(keyName)) {
            return keyState.get(keyName);
        }
        return false;
    }

    public void addKeyMap(String keyName, int keyCode) {
        keyMap.put(keyCode, keyName);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyMap.containsKey(keyCode)) {
            keyState.put(keyMap.get(keyCode), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyMap.containsKey(keyCode)) {
            keyState.put(keyMap.get(keyCode), false);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        for (Map.Entry<String, Boolean> keyStateEntry : keyState.entrySet()) {
            keyStateEntry.setValue(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("mouseClicked at "+ mouseEvent.getX() +","+ mouseEvent.getY());
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
}


