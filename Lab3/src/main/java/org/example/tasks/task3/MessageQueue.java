package org.example.tasks.task3;

public interface MessageQueue {
    void add(Message message) throws InterruptedException;
    Message poll();
}
