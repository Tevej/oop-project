package se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import se.tevej.game.view.rendering.ui.TLabel;

public class LabelLibgdxAdapter extends Label implements TLabel {
    public LabelLibgdxAdapter(CharSequence text, Skin skin) {
        super(text, skin);
    }
}
