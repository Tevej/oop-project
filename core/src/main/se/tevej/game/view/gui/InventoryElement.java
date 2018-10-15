package main.se.tevej.game.view.gui;

import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.view.gamerendering.base.RenderingFactory;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TUiElement;

public class InventoryElement implements TUiElement {
    private TLabel label;
    private TImage image;
    private ResourceType resourceType;

    public InventoryElement(RenderingFactory renderingFactory, int amount,
                            String imagePath, ResourceType resourceType) {
        this.resourceType = resourceType;
        label = renderingFactory.createLabel().text(Integer.toString(amount));
        image = renderingFactory.createImage().image(imagePath);
    }

    public void update(int newAmount) {
        label.text(Integer.toString(newAmount));
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public TImage getImage() {
        return image;
    }

    public TLabel getLabel() {
        return label;
    }
}
