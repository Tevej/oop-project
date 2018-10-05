package main.se.tevej.game.view.gui;

import com.badlogic.gdx.Gdx;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TTexture;
import main.se.tevej.game.view.rendering.ui.TButton;
import main.se.tevej.game.view.rendering.ui.TImage;
import main.se.tevej.game.view.rendering.ui.TLabel;
import main.se.tevej.game.view.rendering.ui.TTable;

public class InventoryGui {
    private int ImageSize = 32;
    private TTable inventoryTable;

    public InventoryGui(RenderingFactory renderingFactory) {
        TImage image = renderingFactory.createImage();
        image.image("wood.jpg");
        inventoryTable = renderingFactory.createTable()
                .x((Gdx.graphics.getWidth() / 2f))
                .y(Gdx.graphics.getHeight()- ImageSize / 2)
                .grid(2, 2)
                .debug(true);
        inventoryTable.addElement(image).width(ImageSize).height(ImageSize);

    }

    public void update(float deltaTime) {
        inventoryTable.update(deltaTime);
    }

    public void render() {
        inventoryTable.render();
    }
}
