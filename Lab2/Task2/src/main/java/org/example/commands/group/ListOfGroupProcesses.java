package org.example.commands.group;

import org.example.CommandExecutor;
import org.example.Group;
import org.example.MyProcess;
import org.example.StringFormatter;
import org.example.managers.ProcessManager;

import java.io.IOException;
import java.util.List;

import static org.example.managers.GroupManager.tryToFindGroup;

public class ListOfGroupProcesses implements CommandExecutor {
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

        StringFormatter stringFormatter = new StringFormatter();

        List<MyProcess> processes = foundGroup.getProcesses();
        if(processes.size() == 0) {
            System.out.println("Group \"" + groupName + "\"" + " has no processes.");
            return;
        }

        System.out.println("  Pid     Ppid    Command");
        for (MyProcess process : processes) {
            String line = stringFormatter.format(process);
            System.out.println("  " + line);
        }
    }
}
