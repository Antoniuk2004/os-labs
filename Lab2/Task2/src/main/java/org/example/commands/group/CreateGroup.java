package org.example.commands.group;

import org.example.CommandExecutor;
import org.example.Group;
import org.example.MyProcess;

import java.io.IOException;
import java.util.List;

public class CreateGroup implements CommandExecutor {
    @Override
    public void execute(List<Group> listOfGroups, String[] params, List<MyProcess> listOfProcesses) throws IOException {
        if (params.length < 2) {
            System.out.println("Specify the group name.");
            return;
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "");
        processBuilder.start();

        String groupName = params[1];
        listOfGroups.add(new Group(groupName));
        System.out.println("Group \"" + groupName + "\" was created.");
    }
}
