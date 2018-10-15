package main.se.tevej.game.view.gui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.TTable;

public class InventoryGui {
    private Entity inventoryEntity;
    private TTable inventoryTable;
    private GuiFactory guiFactory;
    private List<InventoryElement> inventoryElements;

    public InventoryGui(GuiFactory guiFactory, Entity inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
        this.guiFactory = guiFactory;
        create();
    }

    private void create() {
        inventoryElements = new LinkedList<>();
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.WOOD),
            "naturalResources/wood.jpg", ResourceType.WOOD));
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.WATER),
            "naturalResources/water.jpg", ResourceType.WATER));
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.STONE),
            "naturalResources/stone.jpg", ResourceType.STONE));
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.FOOD),
            "food.png", ResourceType.FOOD));
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.POPULATION),
            "population.png", ResourceType.POPULATION));

        int tableHeight = 32;
        inventoryTable = guiFactory.createTable()
            .positionX(Gdx.graphics.getWidth() / 2f)
            .positionY(Gdx.graphics.getHeight() - tableHeight / 2f)
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
