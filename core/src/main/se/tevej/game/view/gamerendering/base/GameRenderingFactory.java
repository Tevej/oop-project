package main.se.tevej.game.view.gamerendering.base;

public interface GameRenderingFactory {

    TBatchRenderer createBatchRenderer();

    TTexture createTexture(String path);

}
