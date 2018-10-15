package main.se.tevej.game.view.gamerendering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;

public abstract class TextureLoader {

    public List<String> imageTypes;

    public TextureLoader() {
        imageTypes = new ArrayList<>();
        imageTypes.add(".jpg");
        imageTypes.add(".png");
        imageTypes.add(".jpeg");
    }

    public List<File> getFilesInDir(String path) throws IllegalArgumentException {

        File folder = new File(path);

        File[] filesAndFolders = folder.listFiles();
        if (filesAndFolders == null) {
            System.out.println("FOLDER NULL?!");
            throw new IllegalArgumentException();
        }

        List<File> files = new ArrayList<>();
        for (final File fileEntry : filesAndFolders) {
            addFileToList(fileEntry, files);
        }

        return files;
    }

    private void addFileToList(File fileEntry, List<File> files) throws IllegalArgumentException {
        if (fileEntry.isDirectory()) {
            getFilesInDir(fileEntry.getPath());
        } else {
            files.add(fileEntry);
        }
    }

    protected abstract void filesToMap(List<File> files, GameRenderingFactory renderingFactory);
}
