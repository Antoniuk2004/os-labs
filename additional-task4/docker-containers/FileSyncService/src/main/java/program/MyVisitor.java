package program;

import program.TCP.DataSender;
import program.managers.FileManager;
import program.params.FileManagerParams;
import program.params.VisitorParams;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;


public class MyVisitor implements FileVisitor<Path> {
    private final FileManager fileManager;
    private final Map<Path, byte[]> fileSystemObjects;
    private boolean first;
    private final Path directory;
    private DataSender dataSender;
    private final FileManagerParams fileManagerParams;

    public MyVisitor(VisitorParams params) {
        this.fileSystemObjects = params.getFileSystemObjects();
        this.first = true;
        this.directory = params.getDirectory();
        this.fileManager = new FileManager();
        fileManagerParams = new FileManagerParams();
        fileManagerParams.setFileSystemObjects(fileSystemObjects);
        this.dataSender = params.getDataSender();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (first) {
            fileSystemObjects.put(dir, null);
        } else {
            FileManagerParams params = new FileManagerParams();
            params.setDirectory(dir);
            params.setFileSystemObjects(fileSystemObjects);
            params.setDataSender(dataSender);

            fileManager.directoryCreating(params);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path filePath = file.toAbsolutePath();
        if (first) {
            byte[] content = Files.readAllBytes(filePath);
            fileSystemObjects.put(filePath, content);
            return FileVisitResult.CONTINUE;
        }

        FileManagerParams params = new FileManagerParams();
        params.setDirectory(file);
        params.setFileSystemObjects(fileSystemObjects);
        params.setDataSender(dataSender);

        if (!fileSystemObjects.containsKey(filePath)) {
            if (file.toString().endsWith("swp")) return FileVisitResult.CONTINUE;

            fileManager.fileCreating(params);
        } else {
            fileManager.fileManaging(params);
        }
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

    public void setFirst(boolean first) {
        this.first = first;
    }
}
