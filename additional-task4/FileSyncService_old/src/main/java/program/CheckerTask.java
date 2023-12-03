package program;

import program.params.CheckerTaskParams;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class CheckerTask implements Runnable {
    private final Checker checker;
    private final Map<Path, byte[]> fileSystemObjects;
    private final Path absolutePathToDirectory;

    public CheckerTask(CheckerTaskParams checkerTaskParams) {
        this.checker = checkerTaskParams.getChecker();
        this.fileSystemObjects = checkerTaskParams.getFileSystemObjects();
        this.absolutePathToDirectory = checkerTaskParams.getAbsolutePathToDirectory();
    }

    @Override
    public void run() {
        while (true) {
            try {
                checker.checkIfCreated(fileSystemObjects, absolutePathToDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            checker.checkIfDeleted(fileSystemObjects);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
