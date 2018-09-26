package se.tevej.game.view;

import com.badlogic.ashley.core.Entity;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.SizeComponent;
import se.tevej.game.view.rendering.RenderingFactory;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TTexture;

public class TextureEntityRenderable implements EntityRenderable {

    private RenderingFactory renderingFactory;
    private TTexture texture;

    public TextureEntityRenderable(String path, RenderingFactory renderingFactory){
        this.renderingFactory = renderingFactory;
        this.texture = renderingFactory.createTexture(path);
    }

    @Override
    public void render(TBatchRenderer batchRenderer, Entity entity) {
        PositionComponent pc = entity.getComponent(PositionComponent.class);
        SizeComponent sc = entity.getComponent(SizeComponent.class);

        batchRenderer.renderTexture(texture, pc.getX(), pc.getY(), sc.getWidth(), sc.getHeight());
    }
}
