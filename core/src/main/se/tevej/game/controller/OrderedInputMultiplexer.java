package main.se.tevej.game.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

import main.se.tevej.game.view.InputProcessorType;
import main.se.tevej.game.view.gui.base.InputProcessorListener;

//Since InputProcessor have a lot of methods, you  can't go around this.
@SuppressWarnings("PMD.TooManyMethods")
public class OrderedInputMultiplexer implements InputProcessor, InputProcessorListener  {

    private Map<InputProcessorType, List<InputProcessor>> processorsMap;

    public OrderedInputMultiplexer() {
        processorsMap = new LinkedHashMap<>();

        processorsMap.put(InputProcessorType.GUI, new LinkedList<>());
        processorsMap.put(InputProcessorType.GAME_RENDERING, new LinkedList<>());
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void addGameRenderingInputProcessor(InputProcessor inputProcessor) {
        add(InputProcessorType.GUI, inputProcessor);
    }

    @Override
    public void addGuiInputProcessor(InputAdapter inputProcessor) {
        add(InputProcessorType.GAME_RENDERING, inputProcessor);
    }

    private void add(InputProcessorType processorType, InputProcessor inputProcessor) {
        try {
            processorsMap.get(processorType).add(inputProcessor);
        } catch (Exception e) {
            System.out.println(
                "Can't find classType: " + processorType + "; in OrderedInputMultiplexer");
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.keyDown(keycode);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.keyUp(keycode);
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.keyTyped(character);
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.touchDown(screenX, screenY, pointer, button);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.touchUp(screenX, screenY, pointer, button);
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.touchDragged(screenX, screenY, pointer);
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.mouseMoved(screenX, screenY);
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.scrolled(amount);
            }
        }
        return false;
    }
}
