package main.java.se.tevej.game.view.gui;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import main.java.se.tevej.game.model.resources.ResourceType;
import main.java.se.tevej.game.view.gui.base.GuiFactory;
import main.java.se.tevej.game.view.gui.base.TImage;
import main.java.se.tevej.game.view.gui.base.TLabel;
import main.java.se.tevej.game.view.gui.base.TTable;

/**
 * A label and an icon for a resource type. InventoryGui will update the current amount
 * of the players inventory for the given resource type.
 */
public class InventoryElement {

    private TLabel label;
    private ResourceType resourceType;

    public InventoryElement(GuiFactory guiFactory, TTable inventoryTable, int amount,
                            String imagePath, ResourceType resourceType) {
        this.resourceType = resourceType;

        initializeElements(guiFactory, inventoryTable, amount, imagePath);
    }

    public void update(int newAmount) {
        label.text(parseDoubleWithSuffix(newAmount));
    }

    private void initializeElements(GuiFactory guiFactory, TTable inventoryTable,
                                    int amount, String imagePath) {
        label = guiFactory.createLabel().text(parseDoubleWithSuffix(amount));
        label.setColor(1, 1, 1, 1);
        TImage image = guiFactory.createImage().image(imagePath);

        inventoryTable.addElement(image).height(32).width(32);
        inventoryTable.addElement(label).height(32).width(70);
    }

    private String parseDoubleWithSuffix(double amount) {
        double result;
        int[] suffixNumbers = {1000, 1000000};
        String suffix = " ";
        if (amount < suffixNumbers[0]) {
            result = amount;
        } else if (amount < suffixNumbers[1]) {
            result = amount / suffixNumbers[0];
            suffix = " K";
        } else {
            result = amount / suffixNumbers[1];
            suffix = " M";
        }
        return round(result) + suffix;
    }

    private String round(double number) {
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(number);
    }

    public ResourceType getResourceType() {
        return resourceType;
    }
}
