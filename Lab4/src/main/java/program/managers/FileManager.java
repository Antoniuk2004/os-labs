package program.managers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public boolean checkIfExists(String path) {
        return Files.exists(Path.of(path));
    }
}
