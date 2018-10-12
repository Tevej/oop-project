package main.se.tevej.game.view;

import main.se.tevej.game.libgdx.view.rendering.RenderingLibgdxFactory;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TTexture;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextureLoader {

    HashMap<Enum<?>, TTexture> textureMap;

    public TextureLoader(RenderingFactory renderingFactory, Enum<?> texturetype, String path) {
        textureMap = new HashMap<>();

        List<String> imageTypes = new ArrayList();
        imageTypes.add(".jpg");
        imageTypes.add(".png");
        imageTypes.add(".jpeg");

        File folder = new File(path);

        try {
            loadTextures(folder, renderingFactory, imageTypes);
        } catch (Exception e) {
            System.out.println("Failed to load textures.");
        }
    }

    public TTexture getTexture(Enum<?> T) {
        return textureMap.get(T);
    }

    private void loadTextures(
            final File folder, RenderingFactory renderingFactory,
            List<String> imageTypes) throws Exception {

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
                        addTextureToMap(fileName, fileEnding, folder, renderingFactory);
                    }
                }
            }
        }
    }

    private void addTextureToMap(String fileName, String fileEnding, File folder, RenderingFactory renderingFactory) {
        String typeName = fileName.substring(0, fileName.length() - fileEnding.length());
        BuildingType type = BuildingType.valueOf(typeName.toUpperCase());
        fileName = folder.getPath() + "/" + fileName;
        textureMap.put(type, renderingFactory.createTexture(fileName));
    }


}
