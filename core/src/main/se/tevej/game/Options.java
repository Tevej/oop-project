package main.se.tevej.game;

public class Options {
    private final int worldWidth;
    private final int worldHeight;

    public Options(int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}
