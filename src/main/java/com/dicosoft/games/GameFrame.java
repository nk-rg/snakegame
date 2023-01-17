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
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int widthGame = 500;
        int heightGame = 500;
        int positionX = (dimension.width - widthGame) / 2;
        int positionY = (dimension.height - heightGame) / 2;
        setBounds(positionX, positionY, widthGame, heightGame);
    }

    public void start() {
        gameContainer.initGame();
    }
}
