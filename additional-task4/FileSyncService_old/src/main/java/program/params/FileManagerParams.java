package program.params;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileManagerParams {
    private Path fileAbsolutePath;
    private Map<Path, byte[]> fileSystemObjects;
    private String directory;
    public Map<Path, byte[]> getFileSystemObjects() {
        return fileSystemObjects;
    }

    public Path getSystemObjectPath() {
        return fileAbsolutePath;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setFileSystemObjects(Map<Path, byte[]> fileSystemObjects) {
        this.fileSystemObjects = fileSystemObjects;
    }

    public void setFileAbsolutePath(Path fileAbsolutePath) {
        this.fileAbsolutePath = fileAbsolutePath;
    }

}
