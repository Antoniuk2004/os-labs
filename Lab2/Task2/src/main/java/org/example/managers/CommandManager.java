package org.example.managers;

import org.example.CommandExecutor;
import org.example.Group;
import org.example.MyProcess;
import org.example.commands.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    private final List<Command> commands;

    public CommandManager() {
        commands = new ArrayList<>();
    }

    public void registerCommand(String command, CommandExecutor executor) {
        commands.add(new Command(command, executor));
    }

    public void executeCommand(String userInputtedText, List<Group> listOfGroups, List<MyProcess> listOfProcesses) throws IOException, InterruptedException {
        String[] userInputtedTextArray = userInputtedText.split(" ");
        String[] params = Arrays.copyOfRange(userInputtedTextArray, 1, userInputtedTextArray.length);

        CommandExecutor executor = null;
        for (Command command : commands) {
            String name = command.getName();
            if (userInputtedText.contains(name)) {
                executor = command.getExecutor();
            }
        }
        if (executor != null) {
            executor.execute(listOfGroups, params, listOfProcesses);
        } else {
            System.out.println(userInputtedText + ": command not found");
        }
    }
}
