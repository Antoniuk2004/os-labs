package program.managers;

import program.TCP.DataSender;
import program.Type;
import program.params.FileManagerParams;
import program.params.JsonParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

public class FileManager {

    public void fileCreating(FileManagerParams params) throws IOException {
        Path directory = params.getDirectory();
        Map<Path, byte[]> fileSystemObjects = params.getFileSystemObjects();

        byte[] content = Files.readAllBytes(directory);
        fileSystemObjects.put(directory, content);

        JsonParams jsonParams = new JsonParams();
        jsonParams.setType(Type.SYSTEM_OBJECT_CREATION);
        jsonParams.setHasContent(true);
        jsonParams.setContent(new String(content));
        jsonParams.setObjectPath(String.valueOf(directory));
        jsonParams.setIsDirectory(false);

        DataSender dataSender = params.getDataSender();
        dataSender.sendData(jsonParams);

        System.out.println(directory + " was created!");
        System.out.println("");
    }

    public void fileManaging(FileManagerParams params) throws IOException {
        Path directory = params.getDirectory();
        Map<Path, byte[]> fileSystemObjects = params.getFileSystemObjects();

        byte[] oldContent = fileSystemObjects.get(directory);
        byte[] newContent = Files.readAllBytes(directory);
        if (!Arrays.equals(oldContent, newContent)) {
            fileSystemObjects.put(directory, newContent);

            JsonParams jsonParams = new JsonParams();
            jsonParams.setType(Type.FILE_MODIFICATION);
            jsonParams.setHasContent(true);
            jsonParams.setContent(new String(newContent));
            jsonParams.setObjectPath(String.valueOf(directory));

            DataSender dataSender = params.getDataSender();
            dataSender.sendData(jsonParams);

            System.out.println(directory + " was modified");
            System.out.println("");
        }
    }

    public void directoryCreating(FileManagerParams params) {
        Path directory = params.getDirectory();
        Map<Path, byte[]> fileSystemObjects = params.getFileSystemObjects();

        if (!fileSystemObjects.containsKey(directory)) {
            fileSystemObjects.put(directory, null);

            JsonParams jsonParams = new JsonParams();
            jsonParams.setType(Type.SYSTEM_OBJECT_CREATION);
            jsonParams.setHasContent(false);
            jsonParams.setObjectPath(String.valueOf(directory));
            jsonParams.setIsDirectory(true);

            DataSender dataSender = params.getDataSender();
            dataSender.sendData(jsonParams);

            System.out.println(directory + " was created!");
            System.out.println("");
        }
    }
}
