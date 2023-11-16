package org.example.tasks.task2;

public class Task2 {
    public void doTask() {
        Once once = new Once();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> once.exec(() ->
                    System.out.println("Done!"))).start();
        }
    }
}
