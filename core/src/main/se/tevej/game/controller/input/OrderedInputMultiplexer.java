package main.se.tevej.game.controller.input;

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

/**
 * This is the central hub for all events and one it's responsibilities
 * is to give priority to gui changes.
 */
public class OrderedInputMultiplexer implements InputProcessor, InputProcessorListener {

    private Map<InputProcessorType, List<InputProcessor>> processorsMap;

    public OrderedInputMultiplexer() {
        processorsMap = new LinkedHashMap<>();
        initializeProcessorsMap();
        connectToLibgdxInput();
    }

    private void initializeProcessorsMap() {
        processorsMap.put(InputProcessorType.GUI, new LinkedList<>());
        processorsMap.put(InputProcessorType.GAME_RENDERING, new LinkedList<>());
    }

    private void connectToLibgdxInput() {
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
        boolean breakProcessing = false;
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                if (inputProcessor.keyDown(keycode)) {
                    breakProcessing = true;
                    break;
                }
            }

            if (breakProcessing) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean breakProcessing = false;
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                if (inputProcessor.keyUp(keycode)) {
                    breakProcessing = true;
                    break;
                }
            }

            if (breakProcessing) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        boolean breakProcessing = false;
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                if (inputProcessor.keyTyped(character)) {
                    breakProcessing = true;
                    break;
                }
            }

            if (breakProcessing) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean breakProcessing = false;
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                if (inputProcessor.touchDown(screenX, screenY, pointer, button)) {
                    breakProcessing = true;
                    break;
                }
            }

            if (breakProcessing) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        boolean breakProcessing = false;
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                if (inputProcessor.touchUp(screenX, screenY, pointer, button)) {
                    breakProcessing = true;
                    break;
                }
            }

            if (breakProcessing) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        boolean breakProcessing = false;
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                if (inputProcessor.touchDragged(screenX, screenY, pointer)) {
                    breakProcessing = true;
                    break;
                }
            }

            if (breakProcessing) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        boolean breakProcessing = false;
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                if (inputProcessor.mouseMoved(screenX, screenY)) {
                    breakProcessing = true;
                    break;
                }
            }

            if (breakProcessing) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        boolean breakProcessing = false;
        for (Map.Entry<InputProcessorType, List<InputProcessor>> entry :
            processorsMap.entrySet()) {
            for (InputProcessor inputProcessor : entry.getValue()) {
                if (inputProcessor.scrolled(amount)) {
                    breakProcessing = true;
                    break;
                }
            }

            if (breakProcessing) {
                break;
            }
        }
        return true;
    }

    public void clear() {
        for (List<InputProcessor> inputProcessor : processorsMap.values()) {
            inputProcessor.clear();
        }
    }
}
