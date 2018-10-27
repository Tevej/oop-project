package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;

/**
 * Entities with components which can affect a certain area around are given a radius component
 * which defines it range.
 */
public class RadiusComponent implements Component {
    private int radius;

    public RadiusComponent(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
