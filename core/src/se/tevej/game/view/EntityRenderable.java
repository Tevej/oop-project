package se.tevej.game.view;

import com.badlogic.ashley.core.Entity;
import se.tevej.game.view.rendering.TBatchRenderer;

public interface EntityRenderable {
    void render(TBatchRenderer batchRenderer, Entity entity, int pixelPerTile);
}
