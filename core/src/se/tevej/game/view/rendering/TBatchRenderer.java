package se.tevej.game.view.rendering;

public interface TBatchRenderer {

    void setCamera(TCamera camera);
    TCamera getCamera();

    void beginRendering();
    void endRendering();
    void dispose();
    void renderTexture(TTexture texture, float x, float y, float width, float height, float rotation, float xScale, float yScale);

    default void renderTexture(TTexture texture, float x, float y, float width, float height){
        renderTexture(texture, x, y, width, height, 0, 1, 1);
    }

    default void renderTexture(TTexture texture, float x, float y){
        renderTexture(texture, x, y, texture.getWidth(), texture.getHeight());
    }

}
