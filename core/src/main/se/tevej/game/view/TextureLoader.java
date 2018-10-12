package main.se.tevej.game.view;

import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TTexture;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class TextureLoader {

    List<String> imageTypes;

    public TextureLoader() {
        imageTypes = new ArrayList();
        imageTypes.add(".jpg");
        imageTypes.add(".png");
        imageTypes.add(".jpeg");
    }

    List<File> getFilesInDir(String path) throws Exception {

        File folder = new File(path);

        File[] filesAndFolders = folder.listFiles();
        if (filesAndFolders == null) {
            System.out.println("FOLDER NULL?!");
            throw new Exception();
        }

        List<File> files = new ArrayList<>();
        for (final File fileEntry : filesAndFolders ) {
            addFileToList(fileEntry, files, path);
        }

        return files;
    }

    private void addFileToList(File fileEntry, List<File> files, String path) throws Exception {
        if (fileEntry.isDirectory()) {
            getFilesInDir(fileEntry.getPath());
        } else {
            files.add(fileEntry);
        }
    }

    abstract void filesToMap(List<File> files, RenderingFactory renderingFactory);
}
