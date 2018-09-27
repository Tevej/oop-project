package se.tevej.game.libgdx.view.rendering;

import com.badlogic.gdx.graphics.Texture;
import se.tevej.game.view.rendering.TTexture;

public class TextureLibgdxAdapter extends Texture implements TTexture {
    public TextureLibgdxAdapter(String internalPath) {
        super(internalPath);
    }
}
