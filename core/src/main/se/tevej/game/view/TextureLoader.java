package main.se.tevej.game.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.se.tevej.game.view.rendering.RenderingFactory;

public abstract class TextureLoader {

    public List<String> imageTypes;

    public TextureLoader() {
        imageTypes = new ArrayList<>();
        imageTypes.add(".jpg");
        imageTypes.add(".png");
        imageTypes.add(".jpeg");
    }

    public List<File> getFilesInDir(String path) throws Exception {

        File folder = new File(path);

        File[] filesAndFolders = folder.listFiles();
        if (filesAndFolders == null) {
            System.out.println("FOLDER NULL?!");
            throw new Exception();
        }

        List<File> files = new ArrayList<>();
        for (final File fileEntry : filesAndFolders) {
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

    protected abstract void filesToMap(List<File> files, RenderingFactory renderingFactory);
}
