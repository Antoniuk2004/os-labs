package program.commands;

public class Command {
    private String name;
    private CommandExecutor commandExecutor;

    public String getName() {
        return name;
    }

    public CommandExecutor getCommandExecutor(){
        return commandExecutor;
    }

    public Command(String name, CommandExecutor commandExecutor){
        this.name = name;
        this.commandExecutor = commandExecutor;
    }
}
