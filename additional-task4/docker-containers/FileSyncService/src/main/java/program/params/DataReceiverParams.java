package program.params;

import program.SharedObject;
import program.Type;
import program.handlers.Handler;

import java.nio.file.Path;
import java.util.Map;

public class DataReceiverParams {
    private Path path;
    private Map<Type, Handler> handlerMap;
    private Map<Path, byte[]> fileSystemObjects;
    private SharedObject sharedObject;

    public Path getRootPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Map<Type, Handler> getHandlerMap() {
        return handlerMap;
    }

    public void setHandlerMap(Map<Type, Handler> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public Map<Path, byte[]> getFileSystemObjects() {
        return fileSystemObjects;
    }

    public void setFileSystemObjects(Map<Path, byte[]> fileSystemObjects) {
        this.fileSystemObjects = fileSystemObjects;
    }

    public SharedObject getSharedObject() {
        return sharedObject;
    }

    public void setSharedObject(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }
}
