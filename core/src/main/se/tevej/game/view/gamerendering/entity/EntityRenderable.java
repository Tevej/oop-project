package main.se.tevej.game.view.gamerendering.entity;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.view.gamerendering.base.TBatchRenderer;

public interface EntityRenderable {
    void render(float offsetX, float offsetY, TBatchRenderer batchRenderer,
                Entity entity, float pixelPerTile);
}
