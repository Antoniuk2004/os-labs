package org.example;

import java.io.IOException;

public class MyProcess {
    private final int pid;
    private final int ppid;
    private final String command;

    public int getPid() {
        return pid;
    }

    public int getPpid() {
        return ppid;
    }

    public String getCommand() {
        return command;
    }

    public static void kill(int pin) throws IOException, InterruptedException {
        Process process =  Runtime.getRuntime().exec(new String[]{"kill", "-9", pin + ""});
        process.waitFor();
    }

    public MyProcess(int pid, int ppid, String command) {
        this.pid = pid;
        this.ppid = ppid;
        this.command = command;
    }
}
