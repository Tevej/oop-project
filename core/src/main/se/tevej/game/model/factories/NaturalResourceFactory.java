package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.resource.Resource;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;

public class NaturalResourceFactory {

    public static Entity createNaturalResource(float x, float y, Resource resource, EntityManager engine) {
        Entity entity = engine.createEntity();
        entity.add(new NaturalResourceComponent(new Resource(resource)));
        entity.add(new PositionComponent(x,y));
        entity.add(new SizeComponent(1,1));
        return entity;
    }
}
