package program.commands.findCommand;

import program.commands.CommandExecutor;
import program.managers.ArgumentManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FindCommand implements CommandExecutor {
    @Override
    public void exec(String[] args) throws IOException {
        ArgumentManager argumentManager = new ArgumentManager();
        Map<String, ParamsSetter> mapOfParams = argumentManager.getParams(args);

        String workingDir = getWorkingDir(mapOfParams);
        String extension = mapOfParams.get("--ext").getParam();
        String partName = mapOfParams.get("--name-contains").getParam();

        Files.walkFileTree(Path.of(workingDir), new MyVisitor(extension, partName));
    }

    private String getWorkingDir(Map<String, ParamsSetter> mapOfParams) {
        String workingDir = mapOfParams.get("--dir").getParam();
        if (workingDir == null) {
            workingDir = System.getProperty("user.dir");
        }
        return workingDir;
    }
}
