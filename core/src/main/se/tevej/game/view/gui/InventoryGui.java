package main.se.tevej.game.view.gui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.resource.ResourceType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TTexture;
import main.se.tevej.game.view.rendering.ui.*;

import java.util.EnumMap;
import java.util.Map;

public class InventoryGui {
    private int ImageSize = 32;
    private Entity inventoryEntity;
    private TTable inventoryTable;
    private RenderingFactory renderingFactory;
    private TLabel textField;

    public InventoryGui(RenderingFactory renderingFactory, Entity inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
        this.renderingFactory = renderingFactory;
        create();
    }

    private void create() {
        double woodAmount = findAmountOfResource(ResourceType.WOOD);

        TImage image = renderingFactory.createImage();
        image.image("wood.jpg");

        textField = renderingFactory.createLabel();
        textField.text(String.valueOf(woodAmount));

        inventoryTable = renderingFactory.createTable()
                .x((Gdx.graphics.getWidth() / 2f))
                .y(Gdx.graphics.getHeight()- ImageSize / 2f)
                .grid(2, 2)
                .debug(true);
        inventoryTable.addElement(image).width(ImageSize).height(ImageSize);
        inventoryTable.addElement(textField);

    }

    private double findAmountOfResource(ResourceType resourceType) {
        return inventoryEntity.getComponent(InventoryComponent.class).getAmountOfResource(resourceType);
    }

    public void update(float deltaTime) {
        textField.text(Double.toString(findAmountOfResource(ResourceType.WOOD)));
        inventoryTable.update(deltaTime);
    }

    public void render() {
        inventoryTable.render();
    }
}
