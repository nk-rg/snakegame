package com.dicosoft.games;

import com.dicosoft.games.entities.Fruit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;
import static javax.swing.SwingConstants.*;

public class GameContainer extends JPanel implements ActionListener, KeyListener {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private SnakeHandler snake = null;
    private Fruit fruit = null;
    private int movement;
    private int delayMovement = 200;
    private final Timer timer = new Timer(delayMovement, this);

    public GameContainer() {
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
        setSize(WIDTH, HEIGHT);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
        if (snake.collapse(fruit)) {
            snake.addNewPart();
            increaseSpeed();
            fruit = new Fruit(snake);
        }
        snake.calculateBodyPartsPosition(movement);
        snake.draw(graphics2D);
    }

    private void increaseSpeed() {
        if (delayMovement >= 10) {
            timer.stop();
            delayMovement = delayMovement * 95 / 100;
            timer.setDelay(delayMovement);
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
        delayMovement = 500;
        int snakePositionX = (getWidth() / 4) - (getWidth() / 4 % 10);
        int snakePositionY = (getHeight() / 2) - (getHeight() / 2 % 10);
        Point position = new Point(snakePositionX, snakePositionY);
        snake = new SnakeHandler(position);
        fruit = new Fruit(snake);
        timer.stop();
        timer.setDelay(delayMovement);
        timer.start();
    }

    private void checkGameOver() {
        if (isGameOver()) {
            askPlayAgain();
        }
    }

    private boolean isGameOver() {
        return snake.isOffScreen();
    }

    private void askPlayAgain() {
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
