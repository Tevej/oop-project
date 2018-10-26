package main.java.se.tevej.game.model.components;

import java.util.Objects;

import com.badlogic.ashley.core.Component;

/**
 * All entities meant to appear in the game-world have a position component so that its position
 * can be determined and rendered there.
 */
public class PositionComponent implements Component {
    private int posX;
    private int posY;

    public PositionComponent(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public PositionComponent copy() {
        return new PositionComponent(posX, posY);
    }

    public int getX() {
        return posX;
    }

    public void setX(int x) {
        this.posX = x;
    }

    public int getY() {
        return posY;
    }

    public void setY(int y) {
        this.posY = y;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);

        if (obj instanceof PositionComponent) {
            equals = posX == ((PositionComponent) obj).getX()
                && posY == ((PositionComponent) obj).getY();
        }

        return equals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY * 1.99);
    }

}
