package org.example;

import org.example.MyProcess;

public class StringFormatter {
    public String format(MyProcess process) {
        int pid = process.getPid();
        int ppid = process.getPpid();
        String command = process.getCommand();

        int pidCount = countDigits(pid);
        int ppidCount = countDigits(ppid);

        String pidWithOffset = addOffset(pidCount, pid);
        String ppidWithOffset = addOffset(ppidCount, ppid);

        return pidWithOffset + "   " + ppidWithOffset + "   " + command;
    }

    private String addOffset(int digitsCount, int number){
        int offset = 5 - digitsCount;
        return number + new String(new char[offset]).replace('\0', ' ');
    }

    private int countDigits(int number) {
        int count = 0;
        while (number != 0) {
            number = number / 10;
            count++;
        }
        if(count == 0) return 1;
        else return count;
    }
}
