package main.java.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;

/**
 * When something is rendered in the world the size component specifies
 * what area is it suppoed to cover.
 */
public class SizeComponent implements Component {
    private int width;
    private int height;

    public SizeComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
