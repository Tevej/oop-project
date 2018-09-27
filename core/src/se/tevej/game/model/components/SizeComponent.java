package se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;

public class SizeComponent implements Component {
    private int width;
    private int height;

    public SizeComponent (int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
