package main.se.tevej.game.view.gamerendering.base;

/**
 * To try to separate libgdx and the game, we use the abstract factory pattern. This
 * factory is used for the game rendering.
 */
public interface GameRenderingFactory {

    TBatchRenderer createBatchRenderer();

    TTexture createTexture(String path);

}
