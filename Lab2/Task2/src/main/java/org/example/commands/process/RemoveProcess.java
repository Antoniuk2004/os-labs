package org.example.commands.process;

import org.example.CommandExecutor;
import org.example.Group;
import org.example.MyProcess;
import org.example.managers.GroupManager;
import org.example.managers.ProcessManager;

import java.util.List;

public class RemoveProcess implements CommandExecutor {
    @Override
    public void execute(List<Group> listOfGroups, String[] params, List<MyProcess> listOfProcesses) {
        if (params.length < 3) {
            System.out.println("Specify the group name and pid.");
            return;
        }

        int processPid = 0;
        try {
            processPid = Integer.parseInt(params[1]);
        } catch (Exception e) {
            System.out.println("Process ID must be an integer.");
            return;
        }

        String groupName = params[2];
        Group foundGroup = GroupManager.tryToFindGroup(listOfGroups, groupName);
        MyProcess foundProcess = ProcessManager.tryToFindProcess(listOfProcesses, processPid);

        if (foundGroup == null) {
            System.out.println("Group \"" + groupName + "\" was not found.");
            return;
        }

        if (foundProcess == null) {
            System.out.println("Process \"" + processPid + "\" was not found.");
            return;
        }

        foundGroup.removeProcess(foundProcess);

        System.out.println("Process \"" + processPid + "\" was removed from group \"" + groupName + "\"");
    }
}
