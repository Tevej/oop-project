package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;

public class RadiusComponent implements Component {
    private int radius;

    public RadiusComponent(int radius){
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
