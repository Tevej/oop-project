package main.se.tevej.game.controller.input.libgdximplementation;

import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.input.base.TKeyBoard;
import main.se.tevej.game.controller.input.base.TMouse;
import main.se.tevej.game.view.gui.base.InputProcessorListener;

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
