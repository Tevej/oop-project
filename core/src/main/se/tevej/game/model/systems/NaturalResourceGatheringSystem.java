package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import main.se.tevej.game.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.*;
import main.se.tevej.game.model.resource.Resource;
import main.se.tevej.game.model.resource.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class NaturalResourceGatheringSystem extends EntitySystem{

    private Engine engine;
    private EntityManager em;

    public NaturalResourceGatheringSystem(EntityManager em){
        this.em = em;
    }

    private List<double[]> getLocationsInRadius(int radius, PositionComponent positionComponent){
        List<double[]> locations = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++){
                if ( i == 0 && j == 0)
                    continue;
                locations.add(new double[] {i+positionComponent.getX(), j+positionComponent.getY()});
            }

        }
        return locations;
    }

    private void gatherFromLocation(float deltaTime, Entity tileE, InventoryComponent iC, GathererComponent gc){
        try {
            if (tileE == null) { return; }
            NaturalResourceComponent tileNRC = tileE.getComponent(NaturalResourceComponent.class);
            if (tileNRC != null &&
                    tileNRC.getType() == gc.getResourcePerSecond().getType()) {
                Resource gatheredResource = gc.getGatheredResource(deltaTime);
                tileNRC.extractResource(gatheredResource);
                iC.addResource(gatheredResource);
                System.out.println(iC.getAmountOfResource(ResourceType.WOOD));
            }
        } catch (NotEnoughResourcesException e) {
            tileE.add(new SignalComponent(SignalType.DELETEENTITY));
            em.getSignal().dispatch(tileE);
            System.out.println("Not enough resource left");
        }
    }


    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime){
        ImmutableArray<Entity> gatherers = engine.getEntitiesFor(Family.all(GathererComponent.class, PositionComponent.class).get());
        InventoryComponent iC = engine.getEntitiesFor(Family.all(InventoryComponent.class).get())
                .first().getComponent(InventoryComponent.class);
        for (Entity gatherer :
                gatherers) {
            GathererComponent gc = gatherer.getComponent(GathererComponent.class);
            RadiusComponent rc = gatherer.getComponent(RadiusComponent.class);
            List<double[]> locations = getLocationsInRadius(rc.getRadius(),
                    gatherer.getComponent(PositionComponent.class));
            WorldComponent wc = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
                    .first().getComponent(WorldComponent.class);

            for (double[] loc : locations) {
                Entity tileE = wc.getTileAt((int)loc[0],(int)loc[1]).getComponent(TileComponent.class).getOccupier();
                gatherFromLocation(deltaTime, tileE, iC, gc);
            }
        }
    }

}
