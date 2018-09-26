package se.tevej.game.view;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import se.tevej.game.model.ashley.EntityManager;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.SizeComponent;
import se.tevej.game.model.components.TileComponent;
import se.tevej.game.view.rendering.RenderingFactory;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TTexture;

import java.util.HashMap;
import java.util.Map;

public class View {

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
            entityRenderable.render(tBatchRenderer, entity);
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

    /**
     * I think we need to find a better way of doing this.
     * @return
     */
    private Map<Class<? extends Component>, EntityRenderable> getTypeToRenderables(){
        Map<Class<? extends Component>, EntityRenderable> output = new HashMap<>();

        /**
         * This should be cached somewhere. Maybe in a concrete implementation of RenderingFactory?
         */
        final TTexture tileTexture = renderingFactory.createTexture("badlogic.jpg");
        output.put(TileComponent.class, (batchRenderer, entity) -> {
            PositionComponent pc = entity.getComponent(PositionComponent.class);
            SizeComponent sc = entity.getComponent(SizeComponent.class);

            batchRenderer.renderTexture(tileTexture, pc.getX(), pc.getY(), sc.getWidth(), sc.getHeight());
        });

        return output;
    }

    private interface EntityRenderable{
        void render(TBatchRenderer batchRenderer, Entity entity);
    }

}
