package com.dicosoft.games;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static javax.swing.SwingConstants.*;

public class SnakeHandler {

    private final LinkedList<SnakePart> snakeParts = new LinkedList<>();

    public SnakeHandler(int positionX, int positionY) {
        snakeParts.add(new SnakePart(positionX, positionY, true));
    }

    public void addNewPart() {
        SnakePart tail = snakeParts.getLast();
        snakeParts.add(new SnakePart((int) tail.x, (int) tail.y, false));
    }

    public void calculateHeadPosition(SnakePart head, int movement) {
        switch (movement) {
            case TOP -> head.y -= 10;
            case RIGHT -> head.x += 10;
            case BOTTOM -> head.y += 10;
            case LEFT -> head.x -= 10;
        }
    }

    public void calculateSnakePartsPosition(int movement) {
        SnakePart head = snakeParts.getFirst();
        double nextPositionX = head.x;
        double nextPositionY = head.y;

        List<SnakePart> visibleSnakeParts = snakeParts.stream().filter(SnakePart::isVisible).toList();
        Optional<SnakePart> snakePartHidden = snakeParts.stream().filter(snakePart -> !snakePart.isVisible()).findFirst();

        for (int i = 0; i < visibleSnakeParts.size(); i++) {
            SnakePart snakePart = visibleSnakeParts.get(i);
            if (i == visibleSnakeParts.size() - 1 && snakePartHidden.isPresent()
                    && snakePart.intersects(snakePartHidden.get())) {
                snakePartHidden.get().setVisible(true);
            }
            if (i == 0) {
                calculateHeadPosition(head, movement);
            } else {
                double auxPositionX = snakePart.x;
                double auxPositionY = snakePart.y;
                snakePart.x = nextPositionX;
                snakePart.y = nextPositionY;
                nextPositionX = auxPositionX;
                nextPositionY = auxPositionY;
            }
        }
    }

    public void drawSnake(Graphics2D graphics2D) {
        snakeParts.stream().filter(SnakePart::isVisible).forEach(snakePart -> {
            graphics2D.draw(snakePart);
            graphics2D.fill(snakePart);
        });
    }

    public boolean intersectsHeadAndFruit(Fruit fruit) {
        return snakeParts.getFirst().intersects(fruit);
    }

    public boolean isOffScreen() {
        SnakePart head = snakeParts.getFirst();
        return head.x == 0 || head.x == 490 || head.y == 0 || head.y == 490;
    }

    public LinkedList<SnakePart> getSnakeParts() {
        return snakeParts;
    }
}
