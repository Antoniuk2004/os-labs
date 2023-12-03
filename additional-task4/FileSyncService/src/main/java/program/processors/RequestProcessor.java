package program.processors;

import program.commands.CommandExecutor;
import program.params.ProcessorParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class RequestProcessor {
    private final Path absolutePathToRequest;
    private final Map<String, CommandExecutor> mapOfCommands;

    public RequestProcessor(ProcessorParams params, Map<String, CommandExecutor> mapOfCommands) {
        this.absolutePathToRequest = params.getAbsolutePathToRequest();
        this.mapOfCommands = mapOfCommands;
    }

    public void processRequests() {
        new Thread(() -> {
            try {
                processRequestsInBackground();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void processRequestsInBackground() throws IOException {
        while (true) {
            String[] args = readRequestArguments();
            if(args[0].isEmpty() && args.length == 1) continue;
            executeCommand(args);
            clearRequestFile();
        }
    }

    private String[] readRequestArguments() throws IOException {
        return Files.readString(absolutePathToRequest).split("\\s+");
    }

    private void executeCommand(String[] args) throws IOException {
        if (mapOfCommands.containsKey(args[0])) {
            CommandExecutor commandExecutor = mapOfCommands.get(args[0]);
            commandExecutor.exec(args);
        } else {
            System.out.printf("Command '%s' was not found.", args[0]);
        }
    }

    private void clearRequestFile() throws IOException {
        Files.writeString(absolutePathToRequest, "");
    }
}
