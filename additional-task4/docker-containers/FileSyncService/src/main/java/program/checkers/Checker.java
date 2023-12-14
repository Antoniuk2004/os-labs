package program.checkers;

import program.MyVisitor;
import program.TCP.DataSender;
import program.Type;
import program.params.JsonParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Checker {
    public void checkIfDeleted(Map<Path, byte[]> fileSystemObjects, DataSender dataSender) {
        List<Path> listOfPaths = fileSystemObjects.keySet().stream().toList();
        for (Path path : listOfPaths) {
            if (!Files.exists(path)) {
                fileSystemObjects.remove(path);

                JsonParams jsonParams = new JsonParams();
                jsonParams.setType(Type.SYSTEM_OBJECT_DELETION);
                jsonParams.setObjectPath(String.valueOf(path));

                dataSender.sendData(jsonParams);

                System.out.println(path + " was deleted!");
                System.out.println("");
            }
        }
    }

    public void checkIfCreated(Path directory, MyVisitor myVisitor) throws IOException {
        Files.walkFileTree(directory, myVisitor);
    }
}
