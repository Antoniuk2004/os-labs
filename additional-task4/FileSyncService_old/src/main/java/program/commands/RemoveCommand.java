package program.commands;

import program.params.ProcessorParams;

import java.io.IOException;
import java.nio.file.Path;

public class RemoveCommand implements CommandExecutor {
    private final ProcessorParams params;

    public RemoveCommand(ProcessorParams params) {
        this.params = params;
    }

    @Override
    public void exec(String[] args) throws IOException {
        JsonManager jsonManager = params.getJsonManager();
        Path path = params.getAbsolutePathToDictionaries();
        jsonManager.removeData(path, "idk");
    }
}
