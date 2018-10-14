package main.se.tevej.game.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

public class BuildingEntityRendereable implements EntityRenderable {

    private Map<BuildingType, TTexture> buildingRenderMap;

    public BuildingEntityRendereable(RenderingFactory renderingFactory) {
        buildingRenderMap = new HashMap<>();
        List<String> imageTypes = new ArrayList();
        imageTypes.add(".jpg");
        imageTypes.add(".png");
        imageTypes.add(".jpeg");
        imageTypes.add(".gif");
        File folder = new File("buildings");

        try {
            loadTextures(folder, renderingFactory, imageTypes);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to load textures.");
        }
    }

    private void loadTextures(
        final File folder, RenderingFactory renderingFactory,
        List<String> imageTypes) throws IllegalArgumentException {

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("FOLDER NULL?!");
            throw new IllegalArgumentException();
        }

        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                loadTextures(fileEntry, renderingFactory, imageTypes);
            } else {
                String fileName = fileEntry.getName();
                for (String fileEnding : imageTypes) {
                    if (fileName.endsWith(fileEnding)) {
                        String typeName = fileName.substring(0,
                            fileName.length() - fileEnding.length());
                        BuildingType type = BuildingType.valueOf(
                            typeName.toUpperCase(Locale.ENGLISH)
                        );
                        fileName = folder.getPath() + "/" + fileName;
                        buildingRenderMap.put(type, renderingFactory.createTexture(fileName));
                    }
                }
            }
        }
    }

    @Override
    public void render(
        float offsetX, float offsetY, TBatchRenderer batchRenderer,
        Entity entity, int pixelPerTile) throws Exception {

        BuildingComponent buildingC = entity.getComponent(BuildingComponent.class);
        PositionComponent posC = entity.getComponent(PositionComponent.class);
        SizeComponent sizeC = entity.getComponent(SizeComponent.class);
        float x = (posC.getX() + offsetX) * pixelPerTile;
        float y = (posC.getY() + offsetY) * pixelPerTile;
        float width = sizeC.getWidth() * pixelPerTile;
        float height = sizeC.getHeight() * pixelPerTile;
        batchRenderer.renderTexture(buildingRenderMap.get(
            buildingC.getType()), x, y, width, height);
    }
}
