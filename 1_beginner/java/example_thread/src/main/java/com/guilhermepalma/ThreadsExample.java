package com.guilhermepalma;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsExample {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Simple Start Thread")).start();

        Runnable runnableOne = () -> {
            AtomicInteger atomicInteger = new AtomicInteger();
            while (atomicInteger.get() < 11) {
                try {
                    Thread.sleep(1000L);
                    System.out.println("Thread One: " + atomicInteger.getAndIncrement());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnableTwo = () -> {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            while (atomicInteger.get() > -11) {
                try {
                    Thread.sleep(2000L);
                    System.out.println("Thread Two: " + atomicInteger.getAndDecrement());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // Thread executada a partir de un Runnable
        new Thread(runnableOne).start();
        new Thread(runnableTwo).start();
    }
}
