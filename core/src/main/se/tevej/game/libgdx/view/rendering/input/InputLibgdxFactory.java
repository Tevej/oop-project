package main.se.tevej.game.libgdx.view.rendering.input;

import main.se.tevej.game.controller.input.InputFactory;
import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.TMouse;

public class InputLibgdxFactory implements InputFactory {

    public InputLibgdxFactory() {

    }

    @Override
    public TMouse createMouse() {
        return new MouseLibgdxAdapter();
    }

    @Override
    public TKeyBoard createKeyBoard() {
        return new KeyBoardLibgdxAdapter();
    }
}
