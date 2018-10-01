package se.tevej.game.libgdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import se.tevej.game.input.TKeyBoard;

import java.util.HashMap;
import java.util.Map;

public class KeyBoardlibgdxAdapter implements TKeyBoard {

    public static final Map<Integer, Key> libgdxKeyCodeToKey = new HashMap<>();

    static{
        for(Key key : Key.values()){
            libgdxKeyCodeToKey.put(key.getLibgdxKeyCode(), key);
        }
    }

    @Override
    public TKeyBoard addClickedListener(OnClickedListener onClickedListener) {
        TKeyBoard keyboard = this;
        addToInputMultiplexer(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                onClickedListener.onClicked(keyboard, libgdxKeyCodeToKey.get(keycode));
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
