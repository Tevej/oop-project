package se.tevej.game.view.rendering;

public interface RenderingFactory {

    TBatchRenderer createBatchRenderer();

    TCamera createCamera();

    TTexture createTexture(String path);

}
