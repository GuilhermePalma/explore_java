package com.guilhermepalma;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsExample {
    public static void main(String[] args) {

        // Criação de uma nova Thread
        new Thread(() -> System.out.println("Simple Start Thread")).start();

        // Instancia do Runnable para usar dentro da Thread
        Runnable runnableOne = () -> {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            while (atomicInteger.get() < 11) {
                try {
                    Thread.sleep(1000L);
                    System.out.printf("[%s] Thread One: %d %n", dateNow(), atomicInteger.getAndIncrement());
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
                    System.out.printf("[%s] Thread Two: %d %n", dateNow(), atomicInteger.getAndDecrement());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // Inicialização das Threads executando a partir de um Runnable
        Thread threadOne = new Thread(runnableOne);
        Thread threadTwo = new Thread(runnableTwo);

        // Threads sendo executadas paralelamente
        System.out.println("\n---------\nExecução de Threads (Paralelamente)\n---------\n");
        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join(2000L);
            System.out.printf("[%s] Mensagem executada 2 segundo após a Inicialização da Thread One %n", dateNow());

            threadTwo.join(4000L);
            System.out.printf("[%s] Mensagem executada 4 segundo após a Inicialização da Thread Two %n", dateNow());

            System.out.println("Thread One is Alive: " + threadOne.isAlive());
            System.out.println("Thread Two is Alive: " + threadTwo.isAlive());

            threadOne.join();
            threadTwo.join();
            System.out.printf("[%s] Mensagem executada após a Finalização da ThreadOne e ThreadTwo %n", dateNow());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread One is Alive: " + threadOne.isAlive());
        System.out.println("Thread Two is Alive: " + threadTwo.isAlive());
    }

    private static String dateNow(){
       return Timestamp.from(Instant.now()).toString();
    }

}
