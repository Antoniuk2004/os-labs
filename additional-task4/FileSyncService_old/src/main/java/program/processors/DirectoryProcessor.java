package program.processors;

import program.Checker;
import program.CheckerTask;
import program.MyVisitor;
import program.params.CheckerTaskParams;
import program.params.ProcessorParams;
import program.params.VisitorParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectoryProcessor {
    private final JsonManager jsonManager;
    private final Map<Path, CheckerTask> mapOfCheckerTask;
    private final Checker checker;
    private Map<Path, byte[]> fileSystemObjects;
    private Path absolutePathToDirectory;

    public DirectoryProcessor(ProcessorParams params) {
        this.jsonManager = params.getJsonManager();
        this.mapOfCheckerTask = params.getMapOfCheckerTask();
        this.checker = new Checker();
        this.absolutePathToDirectory = params.getAbsolutePathToDictionaries();
    }

    public void processDirectories() throws IOException {
        Path absolutePathToDirectories = absolutePathToDirectory;
        List<String> directories = jsonManager.readData(absolutePathToDirectories);

        for (String pathToDirectory : directories) {
            fileSystemObjects = new HashMap<>();
            VisitorParams visitorParams = new VisitorParams();
            visitorParams.setDirectory(pathToDirectory);
            visitorParams.setFirst(true);
            visitorParams.setFileSystemObjects(fileSystemObjects);
            Files.walkFileTree(Path.of(pathToDirectory), new MyVisitor(visitorParams));

            CheckerTaskParams checkerTaskParams = initializeCheckerTaskParams(pathToDirectory);
            CheckerTask checkerTask = new CheckerTask(checkerTaskParams);
            checkerTask.run();
            mapOfCheckerTask.put(absolutePathToDirectory, checkerTask);
        }
    }

    private CheckerTaskParams initializeCheckerTaskParams(String pathToDirectory) {
        CheckerTaskParams checkerTaskParams = new CheckerTaskParams();
        checkerTaskParams.setChecker(checker);
        checkerTaskParams.setFileSystemObjects(fileSystemObjects);
        checkerTaskParams.setAbsolutePathToDirectory(Path.of(pathToDirectory));
        return checkerTaskParams;
    }
}