package se.tevej.game.libgdx.view.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TCamera;
import se.tevej.game.view.rendering.TTexture;

public class BatchRendererLibgdxAdapter extends SpriteBatch implements TBatchRenderer {

    private TCamera camera;

    @Override
    public void setCamera(TCamera camera) {
        this.camera = camera;
    }

    @Override
    public TCamera getCamera() {
        return camera;
    }

    @Override
    public void beginRendering() {
        super.begin();

        if(this.camera != null) {
            OrthographicCamera oc = (CameraLibgdxAdapter) this.camera;
            super.setProjectionMatrix(oc.combined);
        }
    }

    @Override
    public void endRendering() {
        super.end();
    }

    @Override
    public void renderTexture(TTexture texture, float x, float y, float width, float height){
        renderTexture(texture, x, y, width, height, 0, 1, 1);
    }

    @Override
    public void renderTexture(TTexture texture, float x, float y){
        renderTexture(texture, x, y, texture.getWidth(), texture.getHeight());
    }


    @Override
    public void renderTexture(TTexture texture, float x, float y, float width, float height, float rotation, float xScale, float yScale) {
        TextureLibgdxAdapter t = (TextureLibgdxAdapter) texture;
        super.draw(t, x, y, width, height);
    }
}
