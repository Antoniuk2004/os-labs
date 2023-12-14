package program;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RootDirectoryVisitor implements FileVisitor<Path> {
    private Map<String, String> files;
    private List<String> directories;
    private Path rootDirectoryPath;

    public RootDirectoryVisitor() {
        files = new HashMap<>();
        directories = new ArrayList<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        Path dirAbsPath = dir.toAbsolutePath();

        if (dirAbsPath == rootDirectoryPath) {
            return FileVisitResult.CONTINUE;
        }

        String pathStr = String.valueOf(dirAbsPath);
        String rootPathStr = String.valueOf(rootDirectoryPath);
        String finalDirPath = pathStr.replace(rootPathStr, "");

        directories.add(finalDirPath);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path fileAbsPath = file.toAbsolutePath();
        String content = Files.readString(fileAbsPath);

        String pathStr = String.valueOf(fileAbsPath);
        String rootPathStr = String.valueOf(rootDirectoryPath);
        String finalFilePath = pathStr.replace(rootPathStr, "");

        files.put(finalFilePath, content);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public List<String> getDirectories() {
        return directories;
    }

    public void setRootDirectoryPath(Path rootDirectoryPath) {
        this.rootDirectoryPath = rootDirectoryPath;
    }
}
