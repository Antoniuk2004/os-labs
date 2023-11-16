package org.example.commands.group;

import org.example.CommandExecutor;
import org.example.Group;
import org.example.MyProcess;

import java.io.IOException;
import java.util.List;

public class KillGroup implements CommandExecutor {
    @Override
    public void execute(List<Group> listOfGroups, String[] params, List<MyProcess> listOfProcesses) throws IOException, InterruptedException {
        if (params.length < 2) {
            System.out.println("Specify the group name.");
            return;
        }

        String groupName = params[1];
        Group foundGroup = tryToFindGroup(listOfGroups, groupName);

        if (foundGroup == null) {
            System.out.println("Group \"" + groupName + "\"" + " was not found.");
            return;
        }

        List<MyProcess> processes = foundGroup.getProcesses();
        for (MyProcess process : processes) {
            process.kill(process.getPid());
        }
        processes.clear();
        System.out.println("Group \"" + groupName + "\" was killed.");
    }

    private static Group tryToFindGroup(List<Group> listOfGroups, String groupName) {
        Group foundGroup = null;

        for (Group group : listOfGroups) {
            if (group.getName().equals(groupName)) {
                foundGroup = group;
                break;
            }
        }

        return foundGroup;
    }
}
