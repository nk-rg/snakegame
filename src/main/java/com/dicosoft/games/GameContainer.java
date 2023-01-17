package com.dicosoft.games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;
import static javax.swing.SwingConstants.*;

public class GameContainer extends JPanel implements ActionListener, KeyListener {
    private int movementTime = 400;
    private final Timer timer = new Timer(movementTime, this);
    private SnakeHandler snakeHandler = null;
    private Fruit fruit = null;
    private int movement;

    public GameContainer() {
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
        setSize(500, 500);
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawSnake(graphics2D);
        drawFruit(graphics2D);
        checkGameOver();
    }

    private void drawSnake(Graphics2D graphics2D) {
        if (snakeHandler.intersectsHeadAndFruit(fruit)) {
            snakeHandler.addNewPart();
            increaseSpeed();
            fruit = new Fruit(snakeHandler);
        }
        snakeHandler.calculateSnakePartsPosition(movement);
        snakeHandler.drawSnake(graphics2D);
    }

    private void increaseSpeed() {
        if (movementTime != 50) {
            timer.stop();
            movementTime -= 50;
            timer.setDelay(movementTime);
            timer.start();
        }
    }

    private void drawFruit(Graphics2D graphics2D) {
        graphics2D.setColor(Color.RED);
        graphics2D.draw(fruit);
        graphics2D.fill(fruit);
    }

    public void initGame() {
        movement = RIGHT;
        movementTime = 500;
        int positionSnakeHeadX = (getWidth() / 4) - (getWidth() / 4 % 10);
        int positionSnakeHeadY = (getHeight() / 2) - (getHeight() / 2 % 10);
        snakeHandler = new SnakeHandler(positionSnakeHeadX, positionSnakeHeadY);
        fruit = new Fruit(snakeHandler);
        timer.stop();
        timer.setDelay(movementTime);
        timer.start();
    }

    private boolean isGameOver() {
        return snakeHandler.isOffScreen();
    }

    private void checkGameOver() {
        if (!isGameOver()) {
            return;
        }
        int confirmDialog = JOptionPane.showConfirmDialog(null, "¿Quieres jugar de nuevo?");
        if (confirmDialog != JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        initGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_UP -> {
                if (movement != BOTTOM) {
                    movement = TOP;
                }
            }
            case VK_RIGHT -> {
                if (movement != LEFT) {
                    movement = RIGHT;
                }
            }
            case VK_DOWN -> {
                if (movement != TOP) {
                    movement = BOTTOM;
                }
            }
            case VK_LEFT -> {
                if (movement != RIGHT) {
                    movement = LEFT;
                }
            }
        }
    }


}
