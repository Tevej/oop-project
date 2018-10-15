package main.se.tevej.game.utils;

public class Options {
    private final int worldWidth;
    private final int worldHeight;
    private final int pixelsPerTile;

    public Options(int worldWidth, int worldHeight, int pixelsPerTile) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.pixelsPerTile = pixelsPerTile;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getPixelsPerTile() {
        return pixelsPerTile;
    }
}
