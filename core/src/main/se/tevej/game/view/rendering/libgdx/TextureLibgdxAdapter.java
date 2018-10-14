package main.se.tevej.game.view.rendering.libgdx;

import com.badlogic.gdx.graphics.Texture;

import main.se.tevej.game.view.rendering.TTexture;

public class TextureLibgdxAdapter extends Texture implements TTexture {
    public TextureLibgdxAdapter(String internalPath) {
        super(internalPath);
    }
}
