package main.se.tevej.game.controller.input.base;

public interface InputFactory {
    TMouse createMouse();

    TKeyBoard createKeyBoard();
}
