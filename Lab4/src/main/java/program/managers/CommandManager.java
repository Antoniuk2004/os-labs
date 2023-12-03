package program.managers;

import program.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<Command> listOfCommands;

    public CommandManager(){
        listOfCommands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        listOfCommands.add(command);
    }

    public List<Command> getListOfCommands() {
        return listOfCommands;
    }
}
