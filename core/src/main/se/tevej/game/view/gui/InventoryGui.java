package main.se.tevej.game.view.gui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.resources.ResourceType;
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
            "naturalResources/wood.png", ResourceType.WOOD));
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.WATER),
            "naturalResources/water.jpg", ResourceType.WATER));
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.STONE),
            "naturalResources/stone.png", ResourceType.STONE));
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.FOOD),
            "food.png", ResourceType.FOOD));
        inventoryElements.add(new InventoryElement(guiFactory,
            findAmountOfResource(ResourceType.POPULATION),
            "population.png", ResourceType.POPULATION));

        int tableDimension = 32;

        inventoryTable = guiFactory.createTable()
            .positionY(
                Gdx.graphics.getHeight() - (tableDimension * inventoryElements.size() / 2f))
            .grid(inventoryElements.size(), 2)
            .backgroundColor(0, 0, 0, 0.7f)
            .alignLeft()
            .padding(5);
        for (InventoryElement inventoryElement : inventoryElements) {
            inventoryTable.addElement(
                inventoryElement.getImage()).height(tableDimension).width(tableDimension);
            inventoryTable.addElement(
                inventoryElement.getLabel()).height(tableDimension);
        }
        alignTable();
    }

    private void alignTable() {
        inventoryTable.positionX(0);
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
        alignTable();
        inventoryTable.update(deltaTime);
    }

    public void render() {
        inventoryTable.render();
    }
}
