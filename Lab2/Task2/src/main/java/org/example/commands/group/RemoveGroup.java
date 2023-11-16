package org.example.commands.group;

import org.example.CommandExecutor;
import org.example.Group;
import org.example.MyProcess;
import org.example.managers.GroupManager;

import java.util.List;

public class RemoveGroup implements CommandExecutor {
    @Override
    public void execute(List<Group> listOfGroups, String[] params, List<MyProcess> listOfProcesses) {
        if (params.length < 2) {
            System.out.println("Specify the group name.");
            return;
        }

        String groupName = params[1];
        Group foundGroup = GroupManager.tryToFindGroup(listOfGroups, groupName);

        listOfGroups.remove(foundGroup);

        if (foundGroup != null) {
            System.out.println("Group \"" + groupName + "\" was removed.");
            return;
        }

        System.out.println("Group \"" + groupName + "\" was not found.");
    }
}
