package main.java.se.tevej.game.controller.input.libgdximplementation;

import main.java.se.tevej.game.controller.input.base.InputFactory;
import main.java.se.tevej.game.controller.input.base.TKeyBoard;
import main.java.se.tevej.game.controller.input.base.TMouse;
import main.java.se.tevej.game.view.gui.base.InputProcessorListener;

/**
 * A factory responsible for creating our Adapters for keyboard and mouse from a specific framework.
 */
public class InputLibgdxFactory implements InputFactory {

    private InputProcessorListener processorListener;

    public InputLibgdxFactory(InputProcessorListener listener) {
        processorListener = listener;
    }

    @Override
    public TMouse createMouse() {
        return new MouseLibgdxAdapter(processorListener);
    }

    @Override
    public TKeyBoard createKeyBoard() {
        return new KeyBoardLibgdxAdapter(processorListener);
    }
}
