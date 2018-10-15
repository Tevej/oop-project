package main.se.tevej.game.controller.input.base.libgdximplementation;

import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.input.base.TKeyBoard;
import main.se.tevej.game.controller.input.base.TMouse;

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
