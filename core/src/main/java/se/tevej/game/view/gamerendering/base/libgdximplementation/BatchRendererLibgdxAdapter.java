package main.java.se.tevej.game.view.gamerendering.base.libgdximplementation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.java.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.java.se.tevej.game.view.gamerendering.base.TTexture;

/**
 * The libGDX implementation of the TBatchRenderer.
 */
public class BatchRendererLibgdxAdapter extends SpriteBatch implements TBatchRenderer {

    public BatchRendererLibgdxAdapter() {
        super();
    }

    @Override
    public void beginRendering() {
        super.begin();
    }

    @Override
    public void endRendering() {
        super.end();
    }

    @Override
    public void renderTexture(TTexture texture, float x, float y, float width, float height) {
        renderTexture(texture, x, y, width, height, 0, 1, 1);
    }

    @Override
    public void renderTexture(TTexture texture, float x, float y) {
        renderTexture(texture, x, y, texture.getWidth(), texture.getHeight());
    }


    @Override
    public void renderTexture(TTexture texture, float x, float y, float width,
                              float height, float rotation, float scaleX, float scaleY) {
        TextureLibgdxAdapter t = (TextureLibgdxAdapter) texture;
        super.draw(t, x, y, width, height);
    }
}
