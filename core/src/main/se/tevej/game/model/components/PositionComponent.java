package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;

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

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
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
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }

}
