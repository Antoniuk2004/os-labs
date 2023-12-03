package program;

import program.commands.*;
import program.params.Paths;
import program.processors.DirectoryProcessor;
import program.params.ProcessorParams;
import program.processors.RequestProcessor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        PathMaker pathMaker = new PathMaker();

        String pathToDirectories = "src/main/resources/directories.json";
        Path absolutePathToDirectories = pathMaker.makePath(pathToDirectories);

        String pathToRequest = "src/main/resources/request.txt";
        Path absolutePathToRequest = pathMaker.makePath(pathToRequest);

        Paths paths = new Paths(absolutePathToDirectories, absolutePathToRequest);
        ProcessorParams processorParams = initializeProcessorParams(paths);
        Map<String, CommandExecutor> mapOfCommands = initializeMapOfCommands(processorParams);

        RequestProcessor requestProcessor = new RequestProcessor(processorParams, mapOfCommands);
        requestProcessor.processRequests();

        DirectoryProcessor directoryProcessor = new DirectoryProcessor(processorParams);
        directoryProcessor.processDirectories();
    }

    private static ProcessorParams initializeProcessorParams(Paths paths) {
        ProcessorParams processorParams = new ProcessorParams();
        processorParams.setAbsolutePathToDictionaries(paths.directories());
        processorParams.setAbsolutePathToRequest(paths.request());
        processorParams.setMapOfCheckerTask(new HashMap<>());
        return processorParams;
    }

    private static Map<String, CommandExecutor> initializeMapOfCommands(ProcessorParams processorParams) {
        Map<String, CommandExecutor> mapOfCommands = Map.of(
                "add", new AddCommand(processorParams),
                "remove", new RemoveCommand(processorParams),
                "list", new ListCommand(processorParams)
        );
        return mapOfCommands;
    }
}