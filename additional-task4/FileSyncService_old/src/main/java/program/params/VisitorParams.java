package program.params;

import java.nio.file.Path;
import java.util.Map;

public class VisitorParams() {
    private Map<Path, byte[]> fileSystemObjects;
    private boolean first;
    private String directory;

    public Map<Path, byte[]> getFileSystemObjects() {
        return fileSystemObjects;
    }

    public void setFileSystemObjects(Map<Path, byte[]> fileSystemObjects) {
        this.fileSystemObjects = fileSystemObjects;
    }

    public boolean getFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
