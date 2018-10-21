package main.se.tevej.game.controller.screen;

public abstract class DigitScreen {

    //Is used in this package.
    @SuppressWarnings("PMD.DefaultPackage")
    /* package */ final ChangeScreen screenChanger;

    //Is used in this package.
    /* package */ DigitScreen(ChangeScreen screenChanger) {
        this.screenChanger = screenChanger;
    }

    public abstract void update(float deltaTime);

    public abstract void render();

    public abstract void dispose();

}
