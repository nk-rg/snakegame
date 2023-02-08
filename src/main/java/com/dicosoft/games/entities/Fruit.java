package com.dicosoft.games.entities;

import com.dicosoft.games.SnakeHandler;

import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Random;

public class Fruit extends Rectangle2D.Double {
    public Fruit(SnakeHandler snakeHandler) {
        Random random = new Random();
        int randomPositionX = getRandomPosition(random);
        int randomPositionY = getRandomPosition(random);
        while (intersectsAny(randomPositionX, randomPositionY, snakeHandler.getSnakeParts())) {
            randomPositionX = getRandomPosition(random);
            randomPositionY = getRandomPosition(random);
        }
        x = randomPositionX;
        y = randomPositionY;
        width = 10;
        height = 10;
    }

    private static int getRandomPosition(Random random) {
        return random.nextInt(1, 47) * 10;
    }

    private boolean intersectsAny(int positionX, int positionY, LinkedList<SnakePart> snakeParts) {
        return snakeParts.stream().anyMatch(snakePart -> snakePart.x == positionX && snakePart.y == positionY);
    }
}
