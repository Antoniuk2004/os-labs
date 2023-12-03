package program.commands.markCommand;

import program.commands.CommandExecutor;
import program.managers.ArgumentManager;
import program.managers.AttributeManager;
import program.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;

public class MarkCommand implements CommandExecutor {
    @Override
    public void exec(String[] args) throws IOException {
        ArgumentManager argumentManager = new ArgumentManager();
        String filePath =  argumentManager.getFilePath(args);

        FileManager fileManager = new FileManager();
        if(fileManager.checkIfExists(filePath)){
            AttributeManager attributeManager = new AttributeManager();
            attributeManager.writeAttribute(Path.of(filePath), "isMarked", "true");
            System.out.println("File was marked!");
        }
        else{
            System.out.println("File was not found!");
        }
    }
}
