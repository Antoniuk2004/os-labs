package org.example.tasks.task3;

public class Task3 {
    public void doTask() throws InterruptedException {
        Queue q = new Queue(3);

        new Thread(() ->{
            int step = 0;
            while (true){
                Message message = new Message("message%d".formatted(step));
                step++;
                try {
                    q.add(message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        for(int i = 0; i < 3; i++){
            new Thread(() ->{
                while (true){
                Message msg =  q.poll();
                System.out.println(msg.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                }
            }).start();
        }
    }
}