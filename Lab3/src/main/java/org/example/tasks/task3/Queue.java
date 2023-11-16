package org.example.tasks.task3;

import java.util.LinkedList;

public class Queue implements MessageQueue {
    private int maxNumOfElements;
    private LinkedList<Message> elements;

    public Queue(int maxNumOfElements) {
        if (maxNumOfElements <= 0) {
            throw new IllegalArgumentException("Count is <= 0");
        }
        elements = new LinkedList<>();
        this.maxNumOfElements = maxNumOfElements;
    }

    @Override
    public synchronized void add(Message message) throws InterruptedException {
        while (maxNumOfElements <= elements.size()) {
            wait();
        }
        elements.add(message);
        notifyAll();
    }

    @Override
    public synchronized Message poll() {
        while (elements.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
        return elements.poll();
    }
}
