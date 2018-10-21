package main.se.tevej.game.controller.screen;

public abstract class DigitScreen {

    protected final ChangeScreen screenChanger;

    DigitScreen(ChangeScreen screenChanger) {
        this.screenChanger = screenChanger;
    }

    public abstract void update(float deltaTime);

    public abstract void render();

    public abstract void dispose();

}
