package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
    private int x;
    private int y;

    public PositionComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PositionComponent) {
            return (x == ((PositionComponent) obj).getX() &&
                    y == ((PositionComponent) obj).getY());
        }
        return super.equals(obj);
    }

}
