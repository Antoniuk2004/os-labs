package org.example;

import org.example.MyProcess;
import org.example.managers.ProcessManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String name;

    private List<MyProcess> processes;

    public Group(String name) {
        this.name = name;
        this.processes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addProcess(MyProcess process) {
        processes.add(process);
    }

    public void removeProcess(MyProcess process) {
        for (MyProcess element : processes) {
            if (element.getCommand().equals(process.getCommand())) {
                processes.remove(element);
                return;
            }
        }

        for (MyProcess elem : processes) {
            System.out.println(elem.getPid());
        }
    }

    public List<MyProcess> getProcesses() {
        return processes;
    }
}
