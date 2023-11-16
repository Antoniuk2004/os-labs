package org.example.commands.process;

import org.example.CommandExecutor;
import org.example.Group;
import org.example.MyProcess;
import org.example.StringFormatter;

import java.util.List;

public class ListOfProcesses implements CommandExecutor {
    @Override
    public void execute(List<Group> listOfGroups, String[] params, List<MyProcess> listOfProcesses) {
        StringFormatter stringFormatter = new StringFormatter();

        System.out.println("  Pid     Ppid    Command");
        for (MyProcess process : listOfProcesses) {
            String line = stringFormatter.format(process);
            System.out.println("  " + line);
        }
    }
}

