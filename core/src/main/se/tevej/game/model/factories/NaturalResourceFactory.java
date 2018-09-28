package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.resource.ResourceType;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;

public class NaturalResourceFactory {
    public static Entity createNaturalStoneResource(float x, float y, EntityManager engine){
        Entity stone = engine.createEntity();
        stone.add(new NaturalResourceComponent(ResourceType.STONE, 1000));
        stone.add(new PositionComponent(x,y));
        stone.add(new SizeComponent(1,1));
        return stone;
    }

    public static Entity createNaturalWoodResource(float x, float y, EntityManager engine){
        Entity wood = engine.createEntity();
        wood.add(new NaturalResourceComponent(ResourceType.WOOD, 1000));
        wood.add(new PositionComponent(x,y));
        wood.add(new SizeComponent(1,1));
        return wood;
    }

    public static Entity createNaturalWaterResource(float x, float y, EntityManager engine){
        Entity water = engine.createEntity();
        water.add(new NaturalResourceComponent(ResourceType.WATER, 1000));
        water.add(new PositionComponent(x,y));
        water.add(new SizeComponent(1,1));
        return water;
    }
}
