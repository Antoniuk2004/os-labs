package org.example.tasks.task1;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Warehouse {
    public enum ItemStatus {AVAILABLE, PRE_ORDER, UNAVAILABLE}

    public ItemStatus fetchBookStatus(String bookTitle) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        int idx = ThreadLocalRandom.current().nextInt(0, ItemStatus.values().length);
        return ItemStatus.values()[idx];
    }

}
