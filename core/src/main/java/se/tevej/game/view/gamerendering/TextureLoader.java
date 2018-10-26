package main.java.se.tevej.game.view.gamerendering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.java.se.tevej.game.view.gamerendering.base.GameRenderingFactory;

/**
 * An abstract class to easily load in images in a directory.
 */
public abstract class TextureLoader {

    protected final String[] imagesTypes;

    public TextureLoader() {
        imagesTypes = new String[] {
            ".jpg", ".png", ".gif"
        };
    }

    protected List<File> getFilesInDir(String path) throws IllegalArgumentException {
        File folder = new File(path);

        File[] filesAndFolders = folder.listFiles();
        if (filesAndFolders == null) {
            throw new IllegalArgumentException(path + " doesn't exist");
        }

        List<File> files = new ArrayList<>();
        for (final File fileEntry : filesAndFolders) {
            addFileToList(fileEntry, files);
        }

        return files;
    }

    protected abstract void filesToMap(List<File> files, GameRenderingFactory renderingFactory);

    private void addFileToList(File fileEntry, List<File> files) throws IllegalArgumentException {
        if (fileEntry.isDirectory()) {
            getFilesInDir(fileEntry.getPath());
        } else {
            files.add(fileEntry);
        }
    }
}
