package program.handlers;

import program.params.HandlerParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class SynchronizationHandler implements Handler {
    @Override
    public void exec(HandlerParams handlerParams) throws IOException {
        checkAndCreateDirsIfNeeded(handlerParams);
        checkAndCreateFilesIfNeeded(handlerParams);
    }

    private void checkAndCreateDirsIfNeeded(HandlerParams handlerParams) throws IOException {
        List<String> directoriesList = handlerParams.getDirectoriesList();
        Path rootPath = handlerParams.getRootPath();

        for (String dirPath : directoriesList) {
            Path finalDirPath = Path.of(rootPath + dirPath);

            if (!Files.exists(finalDirPath)) {
                Files.createDirectory(finalDirPath);
            }
        }
    }

    private void checkAndCreateFilesIfNeeded(HandlerParams handlerParams) throws IOException {
        Map<String, String> filesMap = handlerParams.getFilesMap();
        Path rootPath = handlerParams.getRootPath();

        for (String dirPath : filesMap.keySet()) {
            Path finalDirPath = Path.of(rootPath + dirPath);
            if (!Files.exists(finalDirPath)) {
                String content = filesMap.get(dirPath);

                Files.createFile(finalDirPath);
                Files.writeString(finalDirPath, content);
            }
        }
    }
}
