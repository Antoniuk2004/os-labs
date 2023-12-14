package program.handlers;

import program.managers.PathManager;
import program.params.HandlerParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static java.lang.String.valueOf;

public class SystemObjectCreationHandler implements Handler {
    private final PathManager pathMaker;

    public SystemObjectCreationHandler() {
        this.pathMaker = new PathManager();
    }

    @Override
    public void exec(HandlerParams handlerParams) throws IOException {
        Path path = pathMaker.makePath(handlerParams);

        if (Files.exists(path)) return;

        Map<Path, byte[]> fileSystemObjects = handlerParams.getFileSystemObjects();

        fileSystemObjects.put(path, null);

        if (handlerParams.getIsDirectory()) {
            Files.createDirectory(path);
            return;
        }

        String content = handlerParams.getFileContent();
        fileSystemObjects.put(path, content.getBytes());

        Files.createFile(path);
        Files.writeString(path, content);
    }
}