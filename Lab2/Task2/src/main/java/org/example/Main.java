package org.example;

import org.example.commands.group.CreateGroup;
import org.example.commands.group.ListOfGroupProcesses;
import org.example.commands.group.RemoveGroup;
import org.example.commands.group.KillGroup;
import org.example.commands.process.RemoveProcess;
import org.example.managers.CommandManager;
import org.example.managers.ProcessManager;
import org.example.commands.process.AddProcess;
import org.example.commands.process.ListOfProcesses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Make a command that shows list of processes in a group
        // Make a process tree for a group
        // Make some tests with group stop command
        // Make process kill func synchronous

        CommandManager commandManager = new CommandManager();
        List<Group> listOfGroups = new ArrayList<>();

        ProcessManager processManager = new ProcessManager();

        commandManager.registerCommand("group -create", new CreateGroup());
        commandManager.registerCommand("group -remove", new RemoveGroup());
        commandManager.registerCommand("group -kill", new KillGroup());
        commandManager.registerCommand("group -list", new ListOfGroupProcesses());
        commandManager.registerCommand("process -add", new AddProcess());
        commandManager.registerCommand("process -remove", new RemoveProcess());
        commandManager.registerCommand("process -list", new ListOfProcesses());

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    processManager.inputListOfProcesses();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print(" $ ");

                String userInput = scanner.nextLine();
                List<MyProcess> listOfProcesses = processManager.getListOfProcesses();
                commandManager.executeCommand(userInput, listOfGroups, listOfProcesses);
            }
        }
    }
}
