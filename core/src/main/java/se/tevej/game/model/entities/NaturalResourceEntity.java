package main.java.se.tevej.game.model.entities;

import com.badlogic.ashley.core.Entity;

import main.java.se.tevej.game.model.components.NaturalResourceComponent;
import main.java.se.tevej.game.model.components.PositionComponent;
import main.java.se.tevej.game.model.components.SizeComponent;
import main.java.se.tevej.game.model.resources.Resource;

/**
 * A predefined set of components attached to an entity which represents a resource on the map.
 */
public class NaturalResourceEntity extends Entity {
    public NaturalResourceEntity(int x, int y, Resource resource) {
        super();
        this.add(new NaturalResourceComponent(new Resource(resource)));
        this.add(new PositionComponent(x, y));
        this.add(new SizeComponent(1, 1));
    }
}
