package main.java.se.tevej.game.view.gamerendering.base.libgdximplementation;

import com.badlogic.gdx.graphics.Texture;

import main.java.se.tevej.game.view.gamerendering.base.TTexture;

/**
 * The libGDX implementation of the TTexture.
 */
public class TextureLibgdxAdapter extends Texture implements TTexture {
    public TextureLibgdxAdapter(String internalPath) {
        super(internalPath);
    }
}
