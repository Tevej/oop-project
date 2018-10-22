package main.se.tevej.game.controller;

import main.se.tevej.game.view.gui.ChangeScreen;

public abstract class DigitScreen {

    //This is package private, it is only used in this package.
    @SuppressWarnings("PMD.DefaultPackage")
    /* package */ final ChangeScreen screenChanger;

    //This is package private, it is only used in this package.
    /* package */ DigitScreen(ChangeScreen screenChanger) {
        this.screenChanger = screenChanger;
    }

    public abstract void updateAndRender(float deltaTime);

    public abstract void dispose();

}
