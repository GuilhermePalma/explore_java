package com.guilhermepalma;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicExamples {
    /**
     * As classes Atomic permite que as manipulação dos valores sejam ThreadSafe, fazendo com que a utilização da
     * classe em diferentes Threads, não afetem o valor final.
     */
    private static final AtomicInteger incrementAtomic = new AtomicInteger(-1);
    private static final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    /**
     * Permite criar uma referência Atomica a uma classe específica
     */
    private static final AtomicReference<Object> atomicClass = new AtomicReference<>(new Object());

    private static int incrementError = -1;

    public static void main(String[] args) {
        System.out.println("\nIncremento do Valor sem Syncronized (Com Erro)");
        Runnable runnableWithError = () -> {
            incrementError++;
            System.out.println("Print i: " + incrementError);
        };

        // Será impresso valores fora de ordem e algumas vezes repetidos
        executeToFinishRunnable(runnableWithError);


        System.out.println("\nIncremento do Valor com Atomic");
        Runnable runnableAtomicInteger = () -> {
            incrementError++;
            System.out.println("Print i (ATOMIC): " + incrementAtomic.getAndIncrement());
        };

        // Irá somar corretamente no Inteiro
        executeToFinishRunnable(runnableAtomicInteger);


        System.out.println("\nIncremento do Valor com Atomic Boolean");

        // Troca o valor do booleano caso seja igual
        Runnable runnableBoolean = () -> System.out.println("Atomic Boolean: " + atomicBoolean.compareAndSet(false, true));
        executeToFinishRunnable(runnableBoolean);

        System.out.println("\nIncremento do Valor com Atomic Boolean");

        // Garante o valor anterior e permite atribuir um novo
        Runnable runnableAtomicReference = () -> System.out.println("Atomic Reference: " + atomicClass.getAndSet(new Random().nextInt()));
        executeToFinishRunnable(runnableAtomicReference);
    }


    private static void executeToFinishRunnable(final Runnable runnable) {
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
