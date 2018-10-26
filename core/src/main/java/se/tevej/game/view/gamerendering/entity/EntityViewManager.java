package main.java.se.tevej.game.view.gamerendering.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;

import main.java.se.tevej.game.model.ModelManager;
import main.java.se.tevej.game.model.components.NaturalResourceComponent;
import main.java.se.tevej.game.model.components.TileComponent;
import main.java.se.tevej.game.model.components.buildings.BuildingComponent;
import main.java.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.java.se.tevej.game.view.gamerendering.base.TBatchRenderer;

/**
 * Manages the rendering of entities. At least for those who has been configured to be rendered.
 * This class uses EntityRenderable to generically render different kinds of entities.
 */
public class EntityViewManager {

    /**
     * Dictionary on how a component type should be rendered.
     */
    private Map<Class<? extends Component>, EntityRenderable> typeToRenderable;

    private Map<EntityRenderable, List<Entity>> rendererEntityMap;

    private GameRenderingFactory renderingFactory;

    public EntityViewManager(ModelManager modelManager, GameRenderingFactory renderingFactory) {
        this.renderingFactory = renderingFactory;
        this.rendererEntityMap = new LinkedHashMap<>();

        typeToRenderable = getTypeToRenderables();

        modelManager.addEntityListener(getNewEntityListener());
    }

    public void render(TBatchRenderer batchRenderer, float offsetX,
                       float offsetY, float pixelPerTile) {
        for (Map.Entry<EntityRenderable, List<Entity>> entry : rendererEntityMap.entrySet()) {
            for (Entity entity : entry.getValue()) {
                entry.getKey().render(-offsetX, -offsetY,
                    batchRenderer, entity, pixelPerTile);
            }
        }
    }

    /**
     * Goes through all the components of an added entity
     * also checks if it has a EntityRenderable for it.
     * As soon as it finds one compatible EntityRenderable,
     * it stops searching and adds the entity to the render pool.
     *
     * @return A entity listener that adds suitable entities to Views render pool.
     */
    private EntityListener getNewEntityListener() {
        return new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                for (Component c : entity.getComponents()) {
                    EntityRenderable entityRenderable = typeToRenderable.get(c.getClass());
                    if (entityRenderable != null) {
                        List<Entity> entities;
                        if (rendererEntityMap.containsKey(entityRenderable)) {
                            entities = rendererEntityMap.get(entityRenderable);
                        } else {
                            entities = new ArrayList<>();
                        }
                        entities.add(entity);
                        rendererEntityMap.put(entityRenderable, entities);
                    }
                }
            }

            @Override
            public void entityRemoved(Entity entity) {
                for (Component c : entity.getComponents()) {
                    EntityRenderable entityRenderable = typeToRenderable.get(c.getClass());
                    if (entityRenderable != null) {
                        rendererEntityMap.get(entityRenderable).remove(entity);
                    }
                }
            }
        };
    }

    private Map<Class<? extends Component>, EntityRenderable> getTypeToRenderables() {
        Map<Class<? extends Component>, EntityRenderable> output = new HashMap<>();

        addOutputElement(output, TileComponent.class,
            new TextureEntityRenderable("tile.jpg", renderingFactory));
        addOutputElement(output, NaturalResourceComponent.class,
            new NaturalResourceEntityRenderable(renderingFactory));
        addOutputElement(output, BuildingComponent.class,
            new BuildingEntityRenderable(renderingFactory));

        return output;
    }

    private Map<Class<? extends Component>, EntityRenderable> addOutputElement(
        Map<Class<? extends Component>, EntityRenderable> map,
        Class<? extends Component> type,
        EntityRenderable renderable) {

        map.put(type, renderable);
        rendererEntityMap.put(renderable, new ArrayList<>());
        return map;
    }
}
