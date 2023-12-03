package program.commands;

import program.params.ProcessorParams;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ListCommand implements CommandExecutor {
    private final ProcessorParams params;

    public ListCommand(ProcessorParams params) {
        this.params = params;
    }

    @Override
    public void exec(String[] args) throws IOException {
        if(args.length > 1){
            System.out.println("Too many arguments.");
            return;
        }

        Path path = params.getAbsolutePathToDictionaries();
    }
}