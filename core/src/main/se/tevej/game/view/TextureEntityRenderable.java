package main.se.tevej.game.view;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

public class TextureEntityRenderable implements EntityRenderable {

    private TTexture texture;

    public TextureEntityRenderable(String path, RenderingFactory renderingFactory) {
        this.texture = renderingFactory.createTexture(path);
    }

    @Override
    public void render(float offsetX, float offsetY, TBatchRenderer batchRenderer, Entity entity, int pixelPerTile) {
        PositionComponent pc = entity.getComponent(PositionComponent.class);
        SizeComponent sc = entity.getComponent(SizeComponent.class);

        batchRenderer.renderTexture(texture, (pc.getX() + offsetX) * pixelPerTile,
            (pc.getY() + offsetY) * pixelPerTile, sc.getWidth() * pixelPerTile,
            sc.getHeight() * pixelPerTile);
    }
}
