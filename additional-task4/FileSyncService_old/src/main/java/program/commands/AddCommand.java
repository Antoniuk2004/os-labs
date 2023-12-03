package program.commands;

import program.params.ProcessorParams;

import java.io.IOException;
import java.nio.file.Path;

public class AddCommand implements CommandExecutor {
    private final ProcessorParams params;

    public AddCommand(ProcessorParams params) {
        this.params = params;
    }

    @Override
    public void exec(String[] args) throws IOException {
        if(!checkIfArgsRight(args)) return;

        Path path = params.getAbsolutePathToDictionaries();
        JsonManager jsonManager = params.getJsonManager();
        String data = args[1];
        jsonManager.writeData(path, data);
        System.out.println("Directory %s was added.".formatted(data));
    }

    private boolean checkIfArgsRight(String[] args){
        if (args.length < 2) {
            System.out.println("Specify the name of the directory.");
            return false;
        } else if (args.length > 2) {
            System.out.println("Too many arguments.");
            return false;
        }
        return true;
    }
}
