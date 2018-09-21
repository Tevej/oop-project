package se.tevej.game.view.rendering;

import se.tevej.game.math.TVector2;

public interface RenderingFactory {

    TBatchRenderer createBatchRenderer();

    TCamera createCamera(TVector2 position);

    TTexture createTexture(String path);

}
