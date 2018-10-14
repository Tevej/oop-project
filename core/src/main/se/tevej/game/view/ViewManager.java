package main.se.tevej.game.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;

import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;

public class ViewManager {

    public static final int PIXEL_PER_TILE = 32;

    /**
     * Dictionary on how a component type should be rendered.
     */
    private Map<Class<? extends Component>, EntityRenderable> typeToRenderable;

    private Map<EntityRenderable, List<Entity>> rendererEntityMap;

    private TBatchRenderer batchRenderer;
    private RenderingFactory renderingFactory;

    // The current camera positions in world coordinates.
    private float currCameraPosX;
    private float currCameraPosY;

    public ViewManager(EntityManager entityManager, RenderingFactory renderingFactory) {
        this.renderingFactory = renderingFactory;
        this.batchRenderer = renderingFactory.createBatchRenderer();
        this.rendererEntityMap = new LinkedHashMap<>();

        typeToRenderable = getTypeToRenderables();

        entityManager.addEntityListener(getNewEntityListener());

        currCameraPosX = 0;
        currCameraPosY = 0;
    }

    public void setPosition(float x, float y) {
        this.currCameraPosX = x;
        this.currCameraPosY = y;
    }

    public void render() {
        batchRenderer.beginRendering();

        for (Map.Entry<EntityRenderable, List<Entity>> entry : rendererEntityMap.entrySet()) {
            try {
                for (Entity entity : entry.getValue()) {
                    entry.getKey().render(-currCameraPosX, -currCameraPosY,
                        batchRenderer, entity, PIXEL_PER_TILE);
                }
            } catch (Exception e) {
                // Maybe do stuff here?
            }
        }

        batchRenderer.endRendering();
    }

    /**
     * Goes through all the components of an added entity
     * also checks if it has a EntityRenderable for it.
     * As soon as it finds one compatiable EntityRenderable,
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
            new BuildingEntityRendereable(renderingFactory));

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