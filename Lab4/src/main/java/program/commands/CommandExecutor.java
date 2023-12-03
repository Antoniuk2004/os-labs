package program.commands;

import java.io.IOException;

public interface CommandExecutor {
    void exec(String[] args) throws IOException;
}