package se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import se.tevej.game.ashley.EntityManager;
import se.tevej.game.model.components.NaturalResourceComponent;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.SizeComponent;
import se.tevej.game.model.resource.ResourceType;

public class NaturalResourceFactory {
    public static Entity createNaturalWoodResourceEntity(EntityManager em, float amount, int posX, int posY) {
        Entity entity = em.createEntity();
        entity.add(new NaturalResourceComponent(ResourceType.WOOD, amount));
        entity.add(new PositionComponent(posX, posY));
        entity.add(new SizeComponent(1, 1));
        return entity;
    }

    public static Entity createNaturalStoneResourceEntity(EntityManager em, float amount, int posX, int posY){
        Entity entity = em.createEntity();
        entity.add(new NaturalResourceComponent(ResourceType.STONE, amount));
        entity.add(new PositionComponent(posX,posY));
        entity.add(new SizeComponent(1,1));
        return entity;
    }

    public static Entity createNaturalWaterResourceEntity(EntityManager em, float amount, int posX, int posY){
        Entity entity = em.createEntity();
        entity.add(new NaturalResourceComponent(ResourceType.WATER, amount));
        entity.add(new PositionComponent(posX,posY));
        entity.add(new SizeComponent(1,1));
        return entity;
    }
}
