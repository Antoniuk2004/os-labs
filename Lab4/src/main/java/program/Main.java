package program;

import program.commands.Command;
import program.commands.CommandExecutor;
import program.commands.findCommand.FindCommand;
import program.commands.markCommand.MarkCommand;
import program.commands.unmarkCommand.UnmarkCommand;
import program.managers.CommandManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandManager commandManager = new CommandManager();
        commandManager.addCommand(new Command("find", new FindCommand()));
        commandManager.addCommand(new Command("mark", new MarkCommand()));
        commandManager.addCommand(new Command("unmark", new UnmarkCommand()));

        if(args.length == 0) {
            System.out.println("Specify the command name");
            System.exit(0);
        }

        String inputtedCommandName = args[0];
        boolean isCommandFound = false;
        for (Command command : commandManager.getListOfCommands()) {
            String commandName = command.getName();
            if (inputtedCommandName.equals(commandName)) {
                CommandExecutor commandExecutor = command.getCommandExecutor();
                commandExecutor.exec(args);
                isCommandFound = true;
            }
        }

        if (!isCommandFound) {
            System.out.println("Command \"%s\" does not exist!".formatted(inputtedCommandName));
        }
    }
}