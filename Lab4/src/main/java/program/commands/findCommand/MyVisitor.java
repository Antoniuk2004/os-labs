package program.commands.findCommand;

import program.managers.AttributeManager;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.function.Predicate;

public class MyVisitor implements FileVisitor<Path> {
    private final String extension;
    private final String partName;

    public MyVisitor(String extension, String partName) {
        this.extension = extension;
        this.partName = partName;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        if(hasNoValidExtension(fileName) || hasNoValidPartName(fileName)){
            return FileVisitResult.CONTINUE;
        }

        AttributeManager attributeManager = new AttributeManager();
        try {
            String value = attributeManager.readAttribute(file.toAbsolutePath(), "isMarked");
            if (value.trim().equals("true")) {
                String absolutePath = String.valueOf(file.toAbsolutePath());
                System.out.println(absolutePath);
            }
        } catch (Exception e) {
        }
        return FileVisitResult.CONTINUE;
    }

    private Boolean hasNoValidExtension(String fileName){
        return (extension != null && !fileName.endsWith("." + extension));
    }

    private Boolean hasNoValidPartName(String fileName){
        String fileNameWithoutExt = fileName.split("\\.")[0];
        return (partName != null && !fileNameWithoutExt.contains(partName));
    }

    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
