package main.se.tevej.game.view;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.exceptions.UnknownResourceException;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

public class NaturalResourceEntityRenderable implements EntityRenderable {

    private TTexture water;
    private TTexture stone;
    private TTexture wood;

    public NaturalResourceEntityRenderable(RenderingFactory renderingFactory) {
        this.water = renderingFactory.createTexture("water.jpg");
        this.stone = renderingFactory.createTexture("stone.jpg");
        this.wood = renderingFactory.createTexture("wood.jpg");
    }

    @Override
    public void render(
        float offsetX, float offsetY, TBatchRenderer batchRenderer,
        Entity entity, int pixelPerTile) throws Exception {

        NaturalResourceComponent naturalResourceC =
            entity.getComponent(NaturalResourceComponent.class);

        TTexture image;

        switch (naturalResourceC.getType()) {

            case WATER:
                image = water;
                break;
            case STONE:
                image = stone;
                break;
            case WOOD:
                image = wood;
                break;
            default:
                throw new UnknownResourceException(naturalResourceC.getType());
        }

        PositionComponent positionC = entity.getComponent(PositionComponent.class);

        SizeComponent sizeC = entity.getComponent(SizeComponent.class);

        if (image != null) {
            batchRenderer.renderTexture(image, (positionC.getX() + offsetX) * pixelPerTile,
                (positionC.getY() + offsetY) * pixelPerTile, sizeC.getWidth() * pixelPerTile,
                sizeC.getHeight() * pixelPerTile);
        }
    }
}
