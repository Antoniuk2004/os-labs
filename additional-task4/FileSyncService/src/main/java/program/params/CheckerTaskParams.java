package program.params;

import program.Checker;

import java.nio.file.Path;
import java.util.Map;

public class CheckerTaskParams {
    private Checker checker;
    private Map<Path, byte[]> fileSystemObjects;
    private Path absolutePathToDirectory;

    public Checker getChecker() {
        return checker;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public Map<Path, byte[]> getFileSystemObjects() {
        return fileSystemObjects;
    }

    public void setFileSystemObjects(Map<Path, byte[]> fileSystemObjects) {
        this.fileSystemObjects = fileSystemObjects;
    }

    public Path getAbsolutePathToDirectory() {
        return absolutePathToDirectory;
    }

    public void setAbsolutePathToDirectory(Path absolutePathToDirectory) {
        this.absolutePathToDirectory = absolutePathToDirectory;
    }
}
