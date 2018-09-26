package se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import se.tevej.game.view.rendering.ui.TButton;

public class ButtonLibgdxAdapter extends TextButton implements TButton {
    public ButtonLibgdxAdapter(Skin skin) {
        super("", skin);
    }

    @Override
    public TButton text(String text) {
        super.setText(text);
        return this;
    }

    @Override
    public TButton addListener(OnClickListener onClickListener) {
        super.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                onClickListener.onClick();
            }
        });
        return this;
    }
}
