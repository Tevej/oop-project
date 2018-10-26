package main.java.se.tevej.game.view.gamerendering.base;

public interface TBatchRenderer {
    void beginRendering();

    void endRendering();

    void dispose();

    void renderTexture(TTexture texture, float x, float y, float width, float height,
                       float rotation, float scaleX, float scaleY);

    void renderTexture(TTexture texture, float x, float y, float width, float height);

    void renderTexture(TTexture texture, float x, float y);
}
