package org.example.tasks.task1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    public void doTask() throws InterruptedException {
//        AtomicInteger step = new AtomicInteger();
//        Thread t = new Thread(() -> {
//            while (true) {
//                System.out.println(step.get());
//                step.getAndIncrement();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        t.setDaemon(true);
//        t.start();
//        Thread.sleep(2000);
//
//
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

        Map<String, Warehouse.ItemStatus> mapOfWarehouse = method(titles);
        System.out.println(mapOfWarehouse);


//        CountDownLatch countDownLatch = new CountDownLatch(titles.size()    );
//
//        for (String title : titles) {
//            String copyOfTitle = title;
//            new Thread(() -> {
//                try {
//                    System.out.printf("%s - %s\n", copyOfTitle, warehouse.fetchBookStatus(copyOfTitle));
//                    countDownLatch.countDown();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }).start();
//        }
//        countDownLatch.await();
//
//        System.out.printf(
//                "Time elapsed %dms\n",
//                System.currentTimeMillis() - timeBeforeFirstFetch
//        );

    }

    public Map<String, Warehouse.ItemStatus> method(List<String> titles) throws InterruptedException {
        Warehouse warehouse = new Warehouse();
        Map<String, Warehouse.ItemStatus> mapOfWarehouse = new ConcurrentHashMap<>();

        int num = 2;
        CountDownLatch countDownLatch = new CountDownLatch(num);

        for (int i = 0; i < num; i++) {
            int copyOfI = i;
            new Thread(() -> {
                for (int k = copyOfI * titles.size() / 2; k < titles.size() / 2 * (copyOfI + 1); k++) {
                    String currentTitle = titles.get(k);
                    try {
                        Warehouse.ItemStatus itemStatus = warehouse.fetchBookStatus(currentTitle);
                        mapOfWarehouse.put(currentTitle, itemStatus);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        return mapOfWarehouse;
    }
}
