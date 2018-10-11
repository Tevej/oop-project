package main.se.tevej.game.view;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.view.rendering.TBatchRenderer;

/**
 *  An EntityRenderable is a way of rendering an certain entity.
 *  It specifies which sprites to use, and how and where to render them.
 */
public interface EntityRenderable {
    void render(float offsetX, float offsetY, TBatchRenderer batchRenderer, Entity entity, int pixelPerTile) throws Exception;
}
