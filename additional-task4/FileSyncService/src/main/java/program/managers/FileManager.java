package program.managers;

import program.params.FileManagerParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

public class FileManager {

    public void fileCreating(FileManagerParams params) throws IOException {
        Path fileAbsolutePath = params.getSystemObjectPath();
        Map<Path, byte[]> fileSystemObjects = params.getFileSystemObjects();
//        Path rootPath = params.getDirectory();
        
        byte[] content = Files.readAllBytes(fileAbsolutePath);
        fileSystemObjects.put(fileAbsolutePath, content);

        System.out.println(fileAbsolutePath + " was created!");
    }

    public void fileManaging(FileManagerParams params) throws IOException {
        Path fileAbsolutePath = params.getSystemObjectPath();
        Map<Path, byte[]> fileSystemObjects = params.getFileSystemObjects();

        byte[] oldContent = fileSystemObjects.get(fileAbsolutePath);
        byte[] newContent = Files.readAllBytes(fileAbsolutePath);
        if (!Arrays.equals(oldContent, newContent)) {
            fileSystemObjects.put(fileAbsolutePath, newContent);
            System.out.println(fileAbsolutePath + " was modified");
        }
    }
    
    public void directoryCreating(FileManagerParams params){
        Path dirAbsolutePath = params.getSystemObjectPath();
        Map<Path, byte[]> fileSystemObjects = params.getFileSystemObjects();

        if (!fileSystemObjects.containsKey(dirAbsolutePath)) {
            fileSystemObjects.put(dirAbsolutePath, null);
            System.out.println(dirAbsolutePath + " was created!");
        }
    }
}
