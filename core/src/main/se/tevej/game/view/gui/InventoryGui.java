package main.se.tevej.game.view.gui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.resource.ResourceType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TTexture;
import main.se.tevej.game.view.rendering.ui.*;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class InventoryGui {
    private int ImageSize = 32;
    private Entity inventoryEntity;
    private TTable inventoryTable;
    private RenderingFactory renderingFactory;
    private TLabel textField;
    private LinkedList<InventoryElement> inventoryElements;

    public InventoryGui(RenderingFactory renderingFactory, Entity inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
        this.renderingFactory = renderingFactory;
        create();
    }

    private void create() {
        inventoryElements = new LinkedList<>();
        inventoryElements.add(new InventoryElement(renderingFactory, findAmountOfResource(ResourceType.WOOD), "wood.jpg", ResourceType.WOOD));
        inventoryElements.add(new InventoryElement(renderingFactory, findAmountOfResource(ResourceType.WATER), "water.jpg", ResourceType.WATER));
        inventoryElements.add(new InventoryElement(renderingFactory, findAmountOfResource(ResourceType.STONE), "quarry.jpg", ResourceType.STONE));
        //inventoryElements.add(new InventoryElement(renderingFactory, findAmountOfResource(ResourceType.FOOD), "food.jpg", ResourceType.FOOD));
        //inventoryElements.add(new InventoryElement(renderingFactory, findAmountOfResource(ResourceType.POPULATION), "population.jpg", ResourceType.POPULATION));

        inventoryTable = renderingFactory.createTable()
                .x((Gdx.graphics.getWidth() / 2f))
                .y(Gdx.graphics.getHeight()- ImageSize / 2f)
                .grid(inventoryElements.size()*2, 1)
                .debug(true);
        for (InventoryElement inventoryElement : inventoryElements) {
            inventoryTable.addElement(inventoryElement.getImage()).height(32).width(32);
            inventoryTable.addElement(inventoryElement.getLabel());
        }
    }

    private int findAmountOfResource(ResourceType resourceType) {
        try {
            return (int) inventoryEntity.getComponent(InventoryComponent.class).getAmountOfResource(resourceType);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public void update(float deltaTime) {
        for (InventoryElement inventoryElement : inventoryElements) {
            inventoryElement.update(findAmountOfResource(inventoryElement.getResourceType()));
        }
        inventoryTable.update(deltaTime);
    }

    public void render() {
        inventoryTable.render();
    }
}
