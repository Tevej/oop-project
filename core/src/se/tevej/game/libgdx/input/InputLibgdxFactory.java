package se.tevej.game.libgdx.input;

import se.tevej.game.input.InputFactory;
import se.tevej.game.input.TKeyBoard;
import se.tevej.game.input.TMouse;

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
