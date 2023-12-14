package program.handlers;

import program.managers.PathManager;
import program.params.HandlerParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class SystemObjectDeletionHandler implements Handler {
    private final PathManager pathMaker;

    public SystemObjectDeletionHandler() {
        this.pathMaker = new PathManager();
    }

    @Override
    public void exec(HandlerParams handlerParams) throws IOException {
        Path path = pathMaker.makePath(handlerParams);

        if (!Files.exists(path)) return;

        Map<Path, byte[]> fileSystemObjects = handlerParams.getFileSystemObjects();

        fileSystemObjects.remove(path);

        Files.delete(path);
    }
}
