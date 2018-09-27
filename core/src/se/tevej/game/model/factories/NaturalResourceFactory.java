package se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import se.tevej.game.model.ashley.EntityManager;
import se.tevej.game.model.components.NaturalResourceComponent;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.SizeComponent;
import se.tevej.game.model.resource.Resource;
import se.tevej.game.model.resource.ResourceType;

public class NaturalResourceFactory {
    public static Entity createNaturalStoneResource(float x, float y, EntityManager engine){
        Entity stone = engine.createEntity();
        stone.add(new NaturalResourceComponent(new Resource(1000, ResourceType.STONE)));
        stone.add(new PositionComponent(x,y));
        stone.add(new SizeComponent(1,1));
        return stone;
    }

    public static Entity createNaturalWoodResource(float x, float y, EntityManager engine){
        Entity wood = engine.createEntity();
        wood.add(new NaturalResourceComponent(new Resource(1000, ResourceType.WOOD)));
        wood.add(new PositionComponent(x,y));
        wood.add(new SizeComponent(1,1));
        return wood;
    }

    public static Entity createNaturalWaterResource(float x, float y, EntityManager engine){
        Entity water = engine.createEntity();
        water.add(new NaturalResourceComponent(new Resource(1000, ResourceType.WATER)));
        water.add(new PositionComponent(x,y));
        water.add(new SizeComponent(1,1));
        return water;
    }
}
