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

    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int NUMBER_OF_ROWS = 5;
    private static final int TABLE_DIMENSION = 32;

    private Entity inventoryEntity;
    private TTable inventoryTable;
    private List<InventoryElement> inventoryElements;

    public InventoryGui(GuiFactory guiFactory, Entity inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
        this.inventoryTable = guiFactory.createTable();
        this.inventoryElements = new LinkedList<>();

        initializeTable();
        populateTable(guiFactory);
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

    private void initializeTable() {
        float halfHeight = TABLE_DIMENSION * NUMBER_OF_COLUMNS * NUMBER_OF_ROWS / 2f;

        inventoryTable
            .positionX(0)
            .positionY(Gdx.graphics.getHeight() - halfHeight)
            .grid(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS)
            .backgroundColor(0, 0, 0, 0.7f)
            .alignLeft()
            .padding(5);
    }

    private void populateTable(GuiFactory guiFactory) {
        createInventoryElement(
            guiFactory,
            ResourceType.WOOD,
            "naturalResources/wood.png"
        );
        createInventoryElement(
            guiFactory,
            ResourceType.WATER,
            "naturalResources/water.jpg"
        );
        createInventoryElement(
            guiFactory,
            ResourceType.STONE,
            "naturalResources/stone.png"
        );
        createInventoryElement(
            guiFactory,
            ResourceType.FOOD,
            "food.png"
        );
        createInventoryElement(
            guiFactory,
            ResourceType.CURRENTPOPULATION,
            "population.png"
        );
    }

    private void createInventoryElement(GuiFactory guiFactory,
                                        ResourceType resourceType, String imagePath) {
        InventoryElement inventoryElement = new InventoryElement(
            guiFactory,
            inventoryTable,
            findAmountOfResource(resourceType),
            imagePath,
            resourceType
        );

        this.inventoryElements.add(inventoryElement);
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
}
