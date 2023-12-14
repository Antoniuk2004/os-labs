package program.params;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HandlerParams {
    private Path rootPath;

    private String additionalPath;

    private String fileContent;
    private UUID uuid;
    private boolean isDirectory;
    private boolean hasContent;
    private Map<Path, byte[]> fileSystemObjects;
    private List<String> directoriesList;
    private Map<String, String> filesMap;

    public Path getRootPath() {
        return rootPath;
    }

    public String getAdditionalPath() {
        return additionalPath;
    }

    public void setAdditionalPath(String additionalPath) {
        this.additionalPath = additionalPath;
    }

    public void setRootPath(Path rootPath) {
        this.rootPath = rootPath;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean doesHaveContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public void setFileSystemObjects(Map<Path,byte[]> fileSystemObjects) {
        this.fileSystemObjects = fileSystemObjects;
    }

    public Map<Path, byte[]> getFileSystemObjects() {
        return fileSystemObjects;
    }

    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public boolean getIsDirectory() {
        return isDirectory;
    }

    public void setDirectoriesList(List<String> directoriesList) {
        this.directoriesList = directoriesList;
    }

    public void setFilesMap(Map<String, String> filesMap) {
        this.filesMap = filesMap;
    }

    public List<String> getDirectoriesList() {
        return directoriesList;
    }

    public Map<String, String> getFilesMap() {
        return filesMap;
    }
}
