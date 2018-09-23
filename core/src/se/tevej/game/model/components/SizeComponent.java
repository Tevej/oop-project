package se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;

public class SizeComponent implements Component {
    int width;
    int height;

    public SizeComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
