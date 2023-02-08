package com.dicosoft.games;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GameContainer gameContainer = new GameContainer();
    public GameFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFramePosition();
        add(gameContainer);
        addKeyListener(gameContainer);
        setTitle("Snake game by Dico :D !");
        pack();
    }

    private void setFramePosition() {
        Dimension systemDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int positionX = (systemDimension.width - GameContainer.WIDTH) / 2;
        int positionY = (systemDimension.height - GameContainer.HEIGHT) / 2;
        setBounds(positionX, positionY, GameContainer.WIDTH, GameContainer.HEIGHT);
    }

    public void start() {
        gameContainer.initGame();
    }
}
