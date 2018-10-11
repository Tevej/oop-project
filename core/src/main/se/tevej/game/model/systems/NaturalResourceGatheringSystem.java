package main.se.tevej.game.model.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import main.se.tevej.game.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.RadiusComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.utils.Resource;

public class NaturalResourceGatheringSystem extends EntitySystem {

    private Engine engine;
    private EntityManager em;

    public NaturalResourceGatheringSystem(EntityManager em) {
        this.em = em;
    }

    private List<double[]> getLocationsInRadius(int radius, PositionComponent positionComponent, int maxWidth, int maxHeight) {
        List<double[]> locations = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                double x = i + positionComponent.getX();
                double y = j + positionComponent.getY();
                if (x < 0 || y < 0 || x >= maxWidth || y >= maxHeight) {
                    continue;
                }
                locations.add(new double[] {x, y});
            }

        }
        return locations;
    }

    private void gatherFromLocation(float deltaTime, Entity tileE, InventoryComponent iC, GathererComponent gc) {
        try {
            if (tileE == null) {
                return;
            }
            NaturalResourceComponent tileNRC = tileE.getComponent(NaturalResourceComponent.class);
            if (tileNRC != null &&
                tileNRC.getType() == gc.getResourcePerSecond().getType()) {
                Resource gatheredResource = gc.getGatheredResource(deltaTime);
                tileNRC.extractResource(gatheredResource);
                iC.addResource(gatheredResource);
            }
        } catch (NotEnoughResourcesException e) {
            tileE.add(new SignalComponent(SignalType.DELETEENTITY));
            em.getSignal().dispatch(tileE);
            System.out.println("Not enough utils left");
        }
    }


    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> gatherers = engine.getEntitiesFor(Family.all(GathererComponent.class, PositionComponent.class).get());
        InventoryComponent iC = engine.getEntitiesFor(Family.all(InventoryComponent.class).get())
            .first().getComponent(InventoryComponent.class);
        WorldComponent wc = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
            .first().getComponent(WorldComponent.class);
        int maxWidth = wc.getWidth();
        int maxHeight = wc.getHeight();

        for (Entity gatherer :
            gatherers) {
            GathererComponent gc = gatherer.getComponent(GathererComponent.class);
            RadiusComponent rc = gatherer.getComponent(RadiusComponent.class);

            List<double[]> locations = getLocationsInRadius(rc.getRadius(),
                gatherer.getComponent(PositionComponent.class), maxWidth, maxHeight);

            for (double[] loc : locations) {
                Entity tilE = wc.getTileAt((int) loc[0], (int) loc[1]);
                TileComponent tc = tilE.getComponent(TileComponent.class);
                Entity tileE = tc.getOccupier();
                gatherFromLocation(deltaTime, tileE, iC, gc);
            }
        }
    }

}
