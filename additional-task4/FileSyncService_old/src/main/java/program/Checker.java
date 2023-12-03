package program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Checker {
    public void checkIfDeleted(Map<Path, byte[]> fileSystemObjects) {
        List<Path> listOfPaths = fileSystemObjects.keySet().stream().toList();
        for (Path path : listOfPaths) {
            try {
                if (!Files.exists(path)) {
                    fileSystemObjects.remove(path);
                    System.out.println(path + " was deleted!");
                }
            } catch (Exception e) {
                System.out.println(path + " was deleted!");

            }
        }
    }

    public void checkIfCreated(Map<Path, byte[]> fileSystemObjects, Path directory) throws IOException {
        Files.walkFileTree(directory, new MyVisitor(fileSystemObjects, false));
    }
}
