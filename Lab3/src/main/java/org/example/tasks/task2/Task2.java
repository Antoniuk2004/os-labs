package org.example.tasks.task2;

import java.util.concurrent.CountDownLatch;

public class Task2 {
    public void doTask() throws InterruptedException {
//        myObject myObject = new myObject();
        SyncInt syncInt = new SyncInt();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                syncInt.inc();
                syncInt.add(10);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();

        int num = syncInt.get();
        System.out.printf(String.valueOf(num));


        //        Once once = new Once();
//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> once.exec(() ->
//                    System.out.println("Done!"))).start();
//        }
    }
}
