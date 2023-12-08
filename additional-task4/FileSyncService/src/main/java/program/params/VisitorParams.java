package program.params;

import program.TCP.DataSender;

import java.nio.file.Path;
import java.util.Map;

public class VisitorParams {
    private Map<Path, byte[]> fileSystemObjects;
    private Path directory;
    private DataSender dataSender;

    public Map<Path, byte[]> getFileSystemObjects() {
        return fileSystemObjects;
    }

    public void setFileSystemObjects(Map<Path, byte[]> fileSystemObjects) {
        this.fileSystemObjects = fileSystemObjects;
    }

    public Path getDirectory() {
        return directory;
    }

    public void setDirectory(Path directory) {
        this.directory = directory;
    }

    public void setDataSender(DataSender dataSender) {
        this.dataSender = dataSender;
    }

    public DataSender getDataSender() {
        return dataSender;
    }
}
