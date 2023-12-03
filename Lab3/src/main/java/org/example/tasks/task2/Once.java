package org.example.tasks.task2;

import java.util.concurrent.atomic.AtomicBoolean;

public class Once {
    final AtomicBoolean aBoolean = new AtomicBoolean(false);

    void exec(Runnable r) {
        if (aBoolean.compareAndSet(false, true)) {
            r.run();
        }
    }
}

