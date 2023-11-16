package org.example;

import java.io.IOException;
import java.util.List;

public interface CommandExecutor {
    void execute(List<Group> listOfGroups, String[] params, List<MyProcess> listOfProcesses) throws IOException, InterruptedException;
}
