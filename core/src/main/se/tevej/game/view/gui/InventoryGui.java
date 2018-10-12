package main.se.tevej.game.view.gui;

import java.util.LinkedList;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.ui.TTable;

public class InventoryGui {
    private Entity inventoryEntity;
    private TTable inventoryTable;
    private RenderingFactory renderingFactory;
    private LinkedList<InventoryElement> inventoryElements;

    public InventoryGui(RenderingFactory renderingFactory, Entity inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
        this.renderingFactory = renderingFactory;
        create();
    }

    private void create() {
        inventoryElements = new LinkedList<>();
        inventoryElements.add(new InventoryElement(renderingFactory,
            findAmountOfResource(ResourceType.WOOD), "wood.jpg", ResourceType.WOOD));
        inventoryElements.add(new InventoryElement(renderingFactory,
            findAmountOfResource(ResourceType.WATER), "water.jpg", ResourceType.WATER));
        inventoryElements.add(new InventoryElement(renderingFactory,
            findAmountOfResource(ResourceType.STONE), "stone.jpg", ResourceType.STONE));
        inventoryElements.add(new InventoryElement(renderingFactory,
            findAmountOfResource(ResourceType.FOOD), "food.png", ResourceType.FOOD));
        inventoryElements.add(new InventoryElement(renderingFactory,
            findAmountOfResource(ResourceType.POPULATION),
            "population.png", ResourceType.POPULATION));

        int tableHeight = 32;
        inventoryTable = renderingFactory.createTable()
            .getX(Gdx.graphics.getWidth() / 2f)
            .getY(Gdx.graphics.getHeight() - tableHeight / 2f)
            .grid(inventoryElements.size() * 2, 1)
            .debug(false);
        for (InventoryElement inventoryElement : inventoryElements) {
            inventoryTable.addElement(inventoryElement.getImage()).height(32).width(32);
            inventoryTable.addElement(inventoryElement.getLabel()).height(32).width(50);
        }
    }

    private int findAmountOfResource(ResourceType resourceType) {
        int amount = 0;

        InventoryComponent inventoryC = inventoryEntity.getComponent(InventoryComponent.class);
        if (inventoryC != null) {
            amount = (int) inventoryC.getAmountOfResource(resourceType);
        }
        return amount;
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
