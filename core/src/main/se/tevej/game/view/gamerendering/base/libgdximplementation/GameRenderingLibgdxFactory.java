package main.se.tevej.game.view.gamerendering.base.libgdximplementation;

import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.se.tevej.game.view.gamerendering.base.TTexture;

public class GameRenderingLibgdxFactory implements GameRenderingFactory {

    public GameRenderingLibgdxFactory() {
        super();
    }

    @Override
    public TBatchRenderer createBatchRenderer() {
        return new BatchRendererLibgdxAdapter();
    }

    @Override
    public TTexture createTexture(String path) {
        return new TextureLibgdxAdapter(path);
    }

}
