package main.se.tevej.game.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class BuildingEntityRendereable extends TextureLoader implements EntityRenderable {

    private HashMap<BuildingType, TTexture> buildingRenderMap;

    public BuildingEntityRendereable(RenderingFactory renderingFactory) {
        super();

        List<File> files;
        try {
            files = getFilesInDir("buildings");
        } catch (Exception e) {
            files = new ArrayList<File>() {};
            System.out.println("Failed to load textures.");
        }

        buildingTextureMap = new HashMap<BuildingType, TTexture>(){};

        filesToMap(files, renderingFactory);
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

    @Override
    void filesToMap(List<File> files, RenderingFactory renderingFactory) {
        for (final File fileEntry : files) {
            String fileName = fileEntry.getName();
            for (String fileEnding : imageTypes) {
                if (fileName.endsWith(fileEnding)) {
                    String typeName = fileName.substring(0, fileName.length() - fileEnding.length());
                    BuildingType type = BuildingType.valueOf(typeName.toUpperCase());
                    buildingTextureMap.put(type, renderingFactory.createTexture(fileEntry.getPath()));
                }
            }
        }
    }
}
