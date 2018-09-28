package main.se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import main.se.tevej.game.view.rendering.ui.TTextField;

public class TextFieldLibgdxAdapter extends TextField implements TTextField {
    public TextFieldLibgdxAdapter(Skin skin) {
        super("", skin);
    }

    @Override
    public TTextField set(String text) {
        super.setText(text);
        return this;
    }

    @Override
    public TTextField addListener(OnChangeListener onChangeListener) {
        super.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onChangeListener.onChange(TextFieldLibgdxAdapter.super.getText());
            }
        });
        return this;
    }
}
