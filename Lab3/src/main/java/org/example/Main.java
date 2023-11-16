package org.example;

import org.example.tasks.task1.Task1;
import org.example.tasks.task2.Task2;
import org.example.tasks.task3.Task3;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();

//        task1.doTask();
//        task2.doTask();
        task3.doTask();
    }
}