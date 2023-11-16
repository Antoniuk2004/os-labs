package org.example.managers;

import org.example.MyProcess;

import java.io.IOException;
import java.util.*;

public class ProcessManager {
    List<MyProcess> listOfProcesses;

    public ProcessManager() {
        listOfProcesses = new ArrayList<>();
    }

    public List<MyProcess> getListOfProcesses() {
        return listOfProcesses;
    }

    public void inputListOfProcesses() throws IOException, InterruptedException {
        Process psProcess = Runtime.getRuntime().exec(new String[]{"ps", "-e", "-o", "pid,ppid,comm"});
        psProcess.waitFor();

        listOfProcesses.clear();
        try (var sc = new Scanner(psProcess.getInputStream())) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] values = line.split("\\s+");

                if (values[1].equals("PID"))
                    continue;

                int pid = Integer.parseInt(values[1]);
                int ppid = Integer.parseInt(values[2]);
                String command = getCommand(values);

                listOfProcesses.add(new MyProcess(pid, ppid, command));
            }
        }
    }

    public static MyProcess tryToFindProcess(List<MyProcess> listOfProcesses, int processPid) {
        MyProcess foundProcess = null;

        for (MyProcess process : listOfProcesses) {
            if (process.getPid() == processPid) {
                foundProcess = process;
                break;
            }
        }

        return foundProcess;
    }

    private String getCommand(String[] values) {
        if (values.length == 4)
            return values[3];

        String[] subArray = Arrays.copyOfRange(values, 3, values.length);

        return String.join(" ", subArray);
    }
}
