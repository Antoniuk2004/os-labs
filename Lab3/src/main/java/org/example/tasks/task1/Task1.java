package org.example.tasks.task1;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Task1 {
    public void doTask() throws InterruptedException {
        Warehouse warehouse = new Warehouse();
        List<String> titles = List.of(
                "Harry Potter and the Philosopher's Stone",
                "Harry Potter and the Chamber of Secrets",
                "Harry Potter and the Prisoner of Azkaban",
                "Harry Potter and the Goblet of Fire",
                "Harry Potter and the Half-Blood Prince",
                "Harry Potter and the Deathly Hallows"
        );
        long timeBeforeFirstFetch = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (String title : titles) {
            String copyOfTitle = title;
            new Thread(() -> {
                try {
                    System.out.printf("%s - %s\n", copyOfTitle, warehouse.fetchBookStatus(copyOfTitle));
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        countDownLatch.await();

        System.out.printf(
                "Time elapsed %dms\n",
                System.currentTimeMillis() - timeBeforeFirstFetch
        );

    }
}
