package main.se.tevej.game.view.rendering;

public interface TBatchRenderer {

    void setCamera(TCamera camera);
    TCamera getCamera();

    void beginRendering();
    void endRendering();
    void dispose();

    void renderTexture(TTexture texture, float x, float y, float width, float height, float rotation, float xScale, float yScale);
    void renderTexture(TTexture texture, float x, float y, float width, float height);
    void renderTexture(TTexture texture, float x, float y);
}
