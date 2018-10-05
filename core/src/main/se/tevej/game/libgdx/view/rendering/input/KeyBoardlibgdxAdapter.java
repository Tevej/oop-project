package main.se.tevej.game.libgdx.view.rendering.input;

import com.badlogic.gdx.*;
import main.se.tevej.game.input.TKeyBoard;

import java.util.HashMap;
import java.util.Map;

public class KeyBoardlibgdxAdapter implements TKeyBoard {

    @Override
    public TKeyBoard addClickedListener(OnTappedListener onClickedListener) {
        TKeyBoard keyboard = this;
        addToInputMultiplexer(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                onClickedListener.onTapped(keyboard, keycode);
                return true;
            }
        });
        return this;
    }

    private void addToInputMultiplexer(InputProcessor ip) {
        if(Gdx.input.getInputProcessor() == null){
            InputMultiplexer inputMultiplexer = new InputMultiplexer();
            inputMultiplexer.addProcessor(ip);
            Gdx.input.setInputProcessor(inputMultiplexer);
        }else{
            InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
            inputMultiplexer.addProcessor(ip);
        }
    }
}
