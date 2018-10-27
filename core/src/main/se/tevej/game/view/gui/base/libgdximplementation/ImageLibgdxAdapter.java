package main.se.tevej.game.view.gui.base.libgdximplementation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import main.se.tevej.game.view.gui.base.TImage;

/**
 * The libGDX implementation of the TImage.
 */
public class ImageLibgdxAdapter extends Image implements TImage {

    public ImageLibgdxAdapter() {
        super();
    }

    @Override
    public TImage image(String path) {
        super.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(path))));
        return this;
    }
}
