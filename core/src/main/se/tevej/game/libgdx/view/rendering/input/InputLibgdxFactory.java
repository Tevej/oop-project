package main.se.tevej.game.libgdx.view.rendering.input;

import main.se.tevej.game.input.InputFactory;
import main.se.tevej.game.input.TKeyBoard;
import main.se.tevej.game.input.TMouse;

public class InputLibgdxFactory implements InputFactory {

    @Override
    public TMouse createMouse() {
        return null;
    }

    @Override
    public TKeyBoard createKeyBoard() {
        return null;
    }
}
