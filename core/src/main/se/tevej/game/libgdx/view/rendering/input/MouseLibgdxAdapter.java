package main.se.tevej.game.libgdx.view.rendering.input;

import com.badlogic.gdx.*;
import main.se.tevej.game.input.TMouse;

public class MouseLibgdxAdapter implements TMouse {



    @Override
    public TMouse addDraggedListener(OnDraggedListener onDraggedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                onDraggedListener.onDragged(mouse, MouseButton.LEFT, screenX, screenY);
                return true;
            }

        });

        return this;
    }

    @Override
    public TMouse addClickedListener(OnClickedListener onClickedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                switch(button){
                    case Input.Buttons.LEFT:
                        onClickedListener.onClicked(mouse, MouseButton.LEFT);
                        break;
                    case Input.Buttons.MIDDLE:
                        onClickedListener.onClicked(mouse, MouseButton.MIDDLE);
                        break;
                    case Input.Buttons.RIGHT:
                        onClickedListener.onClicked(mouse, MouseButton.RIGHT);
                        break;
                    default:
                        System.out.println("Unknown mouse button!!");
                }
                return true;
            }
        });
        return this;
    }

    @Override
    public TMouse addMovedListener(OnMovedListener onMovedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                onMovedListener.onMoved(mouse);
                return true;
            }
        });
        return this;
    }

    @Override
    public float getX() {
        return Gdx.input.getX();
    }

    @Override
    public float getY() {
        return Gdx.input.getY();
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
