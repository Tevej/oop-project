package main.se.tevej.game.libgdx.view.rendering.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.view.rendering.ui.TButton;
import main.se.tevej.game.view.rendering.ui.TTable;

import java.util.*;

public class OrderedInputMultiplexer implements InputProcessor {

    private OrderedInputMultiplexer() {
        elementToInputProcessors = new LinkedHashMap<>();

        elementToInputProcessors.put(TTable.class, new LinkedList<>());
        elementToInputProcessors.put(TMouse.class, new LinkedList<>());
        elementToInputProcessors.put(TKeyBoard.class, new LinkedList<>());
        Gdx.input.setInputProcessor(this);
    }

    private Map<Class<?>, List<InputProcessor>> elementToInputProcessors;

    private static OrderedInputMultiplexer orderedInputMultiplexer;

    public static OrderedInputMultiplexer getInstance() {
        if (orderedInputMultiplexer == null) {
            orderedInputMultiplexer = new OrderedInputMultiplexer();
        }
        return orderedInputMultiplexer;
    }

    public void add(Class<?> classType, InputProcessor inputProcessor) {
        try{
            elementToInputProcessors.get(classType).add(inputProcessor);
        }catch(Exception e){
            System.out.println("Can't find classType: " + classType + "; in OrderedInputMultiplexer");
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry : elementToInputProcessors.entrySet()) {
            for(InputProcessor inputProcessor : entry.getValue()){
                if(inputProcessor.keyDown(keycode)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry : elementToInputProcessors.entrySet()) {
            for(InputProcessor inputProcessor : entry.getValue()){
                if(inputProcessor.keyDown(keycode)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry : elementToInputProcessors.entrySet()) {
            for(InputProcessor inputProcessor : entry.getValue()){
                if(inputProcessor.keyTyped(character)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry : elementToInputProcessors.entrySet()) {
            for(InputProcessor inputProcessor : entry.getValue()){
                if(inputProcessor.touchDown(screenX, screenY, pointer, button)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry : elementToInputProcessors.entrySet()) {
            for(InputProcessor inputProcessor : entry.getValue()){
                if(inputProcessor.touchUp(screenX, screenY, pointer, button)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry : elementToInputProcessors.entrySet()) {
            for(InputProcessor inputProcessor : entry.getValue()){
                if(inputProcessor.touchDragged(screenX, screenY, pointer)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry : elementToInputProcessors.entrySet()) {
            for(InputProcessor inputProcessor : entry.getValue()){
                if(inputProcessor.mouseMoved(screenX, screenY)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry : elementToInputProcessors.entrySet()) {
            for(InputProcessor inputProcessor : entry.getValue()){
                if(inputProcessor.scrolled(amount)){
                    return true;
                }
            }
        }
        return false;
    }
}
