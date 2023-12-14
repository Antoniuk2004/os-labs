package program.managers;

import program.RootDirectoryVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileManager {
    private final Path rootDirectoryPath;
    private final PathManager pathManager;

    public FileManager(Path rootDirectoryPath) {
        this.rootDirectoryPath = rootDirectoryPath;
        this.pathManager = new PathManager();
    }

    public void createFile(String objectPath, String content) throws IOException {
        Path finalPath = pathManager.formatServerPath(objectPath, rootDirectoryPath);

        Files.createFile(finalPath);
        Files.writeString(finalPath, content);
    }

    public void createDirectory(String objectPath) throws IOException {
        Path finalPath = pathManager.formatServerPath(objectPath, rootDirectoryPath);

        Files.createDirectory(finalPath);
    }

    public void deleteSystemObject(String objectPath) throws IOException {
        Path finalPath = pathManager.formatServerPath(objectPath, rootDirectoryPath);

        Files.deleteIfExists(finalPath);
    }

    public void modifyFile(String objectPath, String content) throws IOException {
        Path finalPath = pathManager.formatServerPath(objectPath, rootDirectoryPath);

        Files.writeString(finalPath, content);
    }

    public Object[] getFilesAndDirectories() throws IOException {
        RootDirectoryVisitor rootDirectoryVisitor = new RootDirectoryVisitor();
        rootDirectoryVisitor.setRootDirectoryPath(rootDirectoryPath);

        Files.walkFileTree(rootDirectoryPath, rootDirectoryVisitor);

        Map<String, String> files = rootDirectoryVisitor.getFiles();
        List<String> directories = rootDirectoryVisitor.getDirectories();

        return new Object[]{directories, files};
    }
}
