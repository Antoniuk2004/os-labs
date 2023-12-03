package program.commands;

import java.io.IOException;

public interface CommandExecutor {
    public void exec(String[] args) throws IOException;
}
