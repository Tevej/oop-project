package se.tevej.game.view;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import se.tevej.game.model.ashley.EntityManager;
import se.tevej.game.model.components.NaturalResourceComponent;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.SizeComponent;
import se.tevej.game.model.components.TileComponent;
import se.tevej.game.view.rendering.RenderingFactory;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TTexture;

import java.util.HashMap;
import java.util.Map;

public class View {

    private static final int pixelPerTile = 32;

    /**
     * Dictionary on how a component type should be rendered
     */
    private Map<Class<? extends Component>, EntityRenderable> typeToRenderable;

    /**
     * All the entities that should be rendered when render() is called
     */
    private Map<Entity, EntityRenderable> renderPool;

    private TBatchRenderer tBatchRenderer;
    private RenderingFactory renderingFactory;

    public View(EntityManager entityManager, RenderingFactory renderingFactory){
        this.renderingFactory = renderingFactory;
        this.tBatchRenderer = renderingFactory.createBatchRenderer();
        this.renderPool = new HashMap<>();

        typeToRenderable = getTypeToRenderables();

        entityManager.addEntityListener(getNewEntityListener());
    }

    /**
     * Goes through the renderPool and calls the EntityRenderable.
     */
    public void render(){
        tBatchRenderer.beginRendering();
        renderPool.forEach((entity, entityRenderable) -> {
            try {
                entityRenderable.render(tBatchRenderer, entity, pixelPerTile);
            } catch(Exception e) {
                // Maybe do something here.
            }
        });
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
                    if(entityRenderable != null){
                        renderPool.put(entity, entityRenderable);
                    }
                }
            }

            @Override
            public void entityRemoved(Entity entity) {
                renderPool.remove(entity);
            }
        };
    }

    private Map<Class<? extends Component>, EntityRenderable> getTypeToRenderables(){
        Map<Class<? extends Component>, EntityRenderable> output = new HashMap<>();

        output.put(TileComponent.class, new TextureEntityRenderable("tile.jpg", renderingFactory));
        output.put(NaturalResourceComponent.class, new NaturalResourceEntityRenderable(renderingFactory));

        return output;
    }
}
