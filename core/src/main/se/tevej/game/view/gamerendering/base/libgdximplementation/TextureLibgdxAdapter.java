package main.se.tevej.game.view.gamerendering.base.libgdx;

import com.badlogic.gdx.graphics.Texture;

import main.se.tevej.game.view.gamerendering.base.TTexture;

public class TextureLibgdxAdapter extends Texture implements TTexture {
    public TextureLibgdxAdapter(String internalPath) {
        super(internalPath);
    }
}
