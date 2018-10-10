package main.se.tevej.game.view;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.libgdx.view.rendering.RenderingLibgdxFactory;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuildingEntityRendereable implements EntityRenderable {

    HashMap<BuildingType, TTexture> buildingTextureMap;

    public BuildingEntityRendereable(RenderingFactory renderingFactory) {
        buildingTextureMap = new HashMap<>();
        List<String> imageTypes = new ArrayList();
        imageTypes.add(".jpg");
        imageTypes.add(".png");
        imageTypes.add(".jpeg");
        imageTypes.add(".gif");
        File folder = new File("buildings");

        try {
            loadTextures(folder, renderingFactory, imageTypes);
        } catch (Exception e) {
        }
    }

    private void loadTextures(final File folder, RenderingFactory renderingFactory, List<String> imageTypes) throws Exception {
        renderingFactory = new RenderingLibgdxFactory();

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("FOLDER NULL?!");
            throw new Exception();
        }

        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                loadTextures(fileEntry, renderingFactory, imageTypes);
            } else {
                String fileName = fileEntry.getName();
                for (String fileEnding : imageTypes) {
                    if (fileName.endsWith(fileEnding)) {
                        String typeName = fileName.substring(0, fileName.length() - fileEnding.length());
                        BuildingType type = BuildingType.valueOf(typeName.toUpperCase());
                        fileName = folder.getPath() + "/" + fileName;
                        buildingTextureMap.put(type, renderingFactory.createTexture(fileName));
                    }
                }
            }
        }
    }

    @Override
    public void render(float offsetX, float offsetY, TBatchRenderer batchRenderer, Entity entity, int pixelPerTile) throws Exception {
        BuildingComponent buildingC = entity.getComponent(BuildingComponent.class);
        PositionComponent posC = entity.getComponent(PositionComponent.class);
        SizeComponent sizeC = entity.getComponent(SizeComponent.class);
        float x = (posC.getX() + offsetX) * pixelPerTile;
        float y = (posC.getY() + offsetY) * pixelPerTile;
        float width = sizeC.getWidth() * pixelPerTile;
        float height = sizeC.getHeight() * pixelPerTile;
        batchRenderer.renderTexture(buildingTextureMap.get(buildingC.getType()), x, y, width, height);
    }
}
