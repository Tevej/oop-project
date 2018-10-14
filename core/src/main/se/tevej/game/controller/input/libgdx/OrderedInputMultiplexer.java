package main.se.tevej.game.controller.input.libgdx;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.view.rendering.ui.TTable;

public final class OrderedInputMultiplexer implements InputProcessor {

    private Map<Class<?>, List<InputProcessor>> processorsMap;

    private static OrderedInputMultiplexer instance;

    private OrderedInputMultiplexer() {
        processorsMap = new LinkedHashMap<>();

        processorsMap.put(TTable.class, new LinkedList<>());
        processorsMap.put(TMouse.class, new LinkedList<>());
        processorsMap.put(TKeyBoard.class, new LinkedList<>());
        Gdx.input.setInputProcessor(this);
    }

    public static OrderedInputMultiplexer getInstance() {
        if (instance == null) {
            instance = new OrderedInputMultiplexer();
        }
        return instance;
    }

    public void add(Class<?> classType, InputProcessor inputProcessor) {
        try {
            processorsMap.get(classType).add(inputProcessor);
        } catch (Exception e) {
            System.out.println(
                "Can't find classType: " + classType + "; in OrderedInputMultiplexer");
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.keyDown(keycode);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.keyUp(keycode);
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.keyTyped(character);
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.touchDown(screenX, screenY, pointer, button);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.touchUp(screenX, screenY, pointer, button);
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.touchDragged(screenX, screenY, pointer);
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.mouseMoved(screenX, screenY);
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        for (Map.Entry<Class<?>, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                inputProcessor.scrolled(amount);
            }
        }
        return false;
    }
}
