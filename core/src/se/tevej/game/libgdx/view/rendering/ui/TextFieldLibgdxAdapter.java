package se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import se.tevej.game.view.rendering.ui.TTextField;

public class TextFieldLibgdxAdapter extends TextField implements TTextField {
    public TextFieldLibgdxAdapter(String text, Skin skin) {
        super(text, skin);
    }
}
