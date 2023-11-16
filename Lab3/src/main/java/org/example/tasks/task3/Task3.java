package org.example.tasks.task3;

import java.util.concurrent.CountDownLatch;

public class Task3 {
    public void doTask() throws InterruptedException {
        Queue q = new Queue(3);

        for (int i = 0; i < 5; i++) {
            int copyOfI = i;
            new Thread(() -> {
                try {
                    q.add(new Message("message%d".formatted(copyOfI)));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        String msg = q.poll().getMessage();
        System.out.println(msg);
//
//        msg = q.poll().getMessage();
//        System.out.println(msg);
//
//        msg = q.poll().getMessage();
//        System.out.println(msg);
//
//        msg = q.poll().getMessage();
//        System.out.println(msg);
//
//        msg = q.poll().getMessage();
//        System.out.println(msg);
    }
}
