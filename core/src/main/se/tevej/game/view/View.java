package main.se.tevej.game.view;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;

import java.util.*;

public class View {

    private static final int pixelPerTile = 32;

    /**
     * Dictionary on how a component type should be rendered
     */
    private Map<Class<? extends Component>, EntityRenderable> typeToRenderable;

    private Map<EntityRenderable, List<Entity>> rendererToEntityMap;

    private TBatchRenderer tBatchRenderer;
    private RenderingFactory renderingFactory;

    public View(EntityManager entityManager, RenderingFactory renderingFactory) {
        this.rendererToEntityMap = new LinkedHashMap<>();

        this.renderingFactory = renderingFactory;
        this.tBatchRenderer = renderingFactory.createBatchRenderer();
        this.rendererToEntityMap = new LinkedHashMap<>();

        typeToRenderable = getTypeToRenderables();

        entityManager.addEntityListener(getNewEntityListener());
    }

    
    public void render(){
        tBatchRenderer.beginRendering();

        for (Map.Entry<EntityRenderable, List<Entity>> entry : rendererToEntityMap.entrySet()) {
            try {
                for (Entity entity : entry.getValue()) {
                    entry.getKey().render(tBatchRenderer, entity, pixelPerTile);
                }
            } catch (Exception e) {
                // Maybe do stuff here?
            }
        }

        tBatchRenderer.endRendering();
    }

    /**
     * Goes through all the components of an added entity and checks if it has a EntityRenderable for it.
     * As soon as it finds one compatiable EntityRenderable, it stops searching and adds the entity to the render pool.
     * @return A entity listener that adds suitable entities to Views render pool.
     */
    private EntityListener getNewEntityListener(){
        return new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                for(Component c : entity.getComponents()){
                    EntityRenderable entityRenderable = typeToRenderable.get(c.getClass());
                    if(entityRenderable != null) {
                        List<Entity> entities;
                        if (rendererToEntityMap.containsKey(entityRenderable)) {
                            entities = rendererToEntityMap.get(entityRenderable);
                        } else {
                            entities = new ArrayList<>();
                        }
                        entities.add(entity);
                        rendererToEntityMap.put(entityRenderable, entities);
                    }
                }
            }

            @Override
            public void entityRemoved(Entity entity) {
                for (Component c : entity.getComponents()) {
                    EntityRenderable entityRenderable = typeToRenderable.get(c.getClass());
                    if (entityRenderable != null) {
                        rendererToEntityMap.get(entityRenderable).remove(entity);
                    }
                }
            }
        };
    }

    private Map<Class<? extends Component>, EntityRenderable> getTypeToRenderables() {
        Map<Class<? extends Component>, EntityRenderable> output = new HashMap<>();

        output.put(TileComponent.class, new TextureEntityRenderable("tile.jpg", renderingFactory));
        output.put(NaturalResourceComponent.class, new NaturalResourceEntityRenderable(renderingFactory));
        output.put(BuildingComponent.class, new BuildingEntityRendereable(renderingFactory));

        return output;
    }
}
