package org.example.tasks.task2;

public class SyncInt {
    volatile int num;

    public synchronized void inc() {
        num++;
    }

    public synchronized void add(int value) {
        num += value;
    }

    public int get() {
        return num;
    }
}
