package main.se.tevej.game.model.entities;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.utils.Resource;

public class NaturalResourceEntity extends Entity {
    public NaturalResourceEntity(int x, int y, Resource resource) {
        super();
        this.add(new NaturalResourceComponent(new Resource(resource)));
        this.add(new PositionComponent(x, y));
        this.add(new SizeComponent(1, 1));
    }
}
