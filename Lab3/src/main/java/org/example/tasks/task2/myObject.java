package org.example.tasks.task2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class myObject {

    private volatile Object object;
    final AtomicBoolean set = new AtomicBoolean(false);

    public synchronized void setObject(Object object) throws InterruptedException {
        if (object != null) {
            this.object = object;
        }
    }

    public Object getObject() {
        return object;
    }
}

