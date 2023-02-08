package com.dicosoft.games.entities;

import java.awt.geom.Rectangle2D;

public class SnakePart extends Rectangle2D.Double {
    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public SnakePart(int positionX, int positionY, boolean visible) {
       super.width = 10;
       super.height = 10;
       super.x = positionX;
       super.y = positionY;
       setVisible(visible);
   }
}
