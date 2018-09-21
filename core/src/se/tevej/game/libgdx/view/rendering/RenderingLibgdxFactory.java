package se.tevej.game.libgdx.view.rendering;

import se.tevej.game.math.TVector2;
import se.tevej.game.view.rendering.RenderingFactory;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TTexture;

public class RenderingLibgdxFactory implements RenderingFactory {

    @Override
    public TBatchRenderer createBatchRenderer() {
        return new BatchRendererLibgdxAdapter();
    }

    public CameraLibgdxAdapter createCamera(TVector2 position) {
        return new CameraLibgdxAdapter(position);
    }

    @Override
    public TTexture createTexture(String path) {
        return new TextureLibgdxAdapter(path);
    }
}
