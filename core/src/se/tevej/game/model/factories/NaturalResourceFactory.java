package se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import se.tevej.game.model.ashley.EntityManager;
import se.tevej.game.model.components.NaturalResourceComponent;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.SizeComponent;
import se.tevej.game.model.resource.ResourceType;

public class NaturalResourceFactory {
    public static Entity createNaturalStoneResource(float x, float y, EntityManager engine){
        Entity stone = engine.createEntity();
        stone.add(new NaturalResourceComponent(ResourceType.STONE, 1000));
        stone.add(new PositionComponent(x*32,y*32));
        stone.add(new SizeComponent(1*32,1*32));
        return stone;
    }

    public static Entity createNaturalWoodResource(float x, float y, EntityManager engine){
        Entity wood = engine.createEntity();
        wood.add(new NaturalResourceComponent(ResourceType.WOOD, 1000));
        wood.add(new PositionComponent(x*32,y*32));
        wood.add(new SizeComponent(32,32));
        return wood;
    }

    public static Entity createNaturalWaterResource(float x, float y, EntityManager engine){
        Entity water = engine.createEntity();
        water.add(new NaturalResourceComponent(ResourceType.WATER, 1000));
        water.add(new PositionComponent(x*32,y*32));
        water.add(new SizeComponent(32,32));
        return water;
    }
}
