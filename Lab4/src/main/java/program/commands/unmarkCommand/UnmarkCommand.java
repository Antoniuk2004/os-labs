package program.commands.unmarkCommand;

import program.commands.CommandExecutor;
import program.managers.ArgumentManager;
import program.managers.AttributeManager;
import program.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;

public class UnmarkCommand implements CommandExecutor {
    @Override
    public void exec(String[] args) throws IOException {
        ArgumentManager argumentManager = new ArgumentManager();
        String filePath = argumentManager.getFilePath(args);

        FileManager fileManager = new FileManager();
        if (fileManager.checkIfExists(filePath)) {
            AttributeManager attributeManager = new AttributeManager();
            String result = attributeManager.readAttribute(Path.of(filePath), "isMarked");
            if (result.trim().equals("true")) {
                attributeManager.writeAttribute(Path.of(filePath), "isMarked", "false");
                System.out.println("File was unmarked!");
            }
            else{
                System.out.println("File is not marked!");
            }
        } else {
            System.out.println("File was not found!");
        }
    }
}
