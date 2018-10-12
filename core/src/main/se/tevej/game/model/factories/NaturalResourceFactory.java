package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.utils.Resource;

@SuppressWarnings("PMD") // The class is to be removed.
public class NaturalResourceFactory {

    public static Entity createNaturalResource(
        int x, int y, Resource resource, EntityManager engine) {
        Entity entity = engine.createEntity();
        entity.add(new NaturalResourceComponent(new Resource(resource)));
        entity.add(new PositionComponent(x, y));
        entity.add(new SizeComponent(1, 1));
        return entity;
    }
}
