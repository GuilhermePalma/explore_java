package com.guilhermepalma;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsThreadsafeExamples {

    /**
     * {@link CopyOnWriteArrayList} se trata de uma implementação de List que pode ser acessada em diferentes Thread. Entretanto,
     * ocupa mais espaço na memoria por copiar o conteudo inteiro da lista para que gravar ou remover uma informação
     */
    private static List<String> listThreadSafe;

    /**
     * {@link ConcurrentHashMap} será implementado nessa variavel, tornando-o Thread Seafe, ou seja, executará as
     * opreações de inserção e exclusão de elementos sem que a Thread interfirá. Possui um desempenho menor que o
     * {@link HashMap}, mas garante que os Itens sejam manipualdos e garantidos em Multithreads
     */
    private static Map<Integer, String> mapThreadSafe;

    private static List<String> listNoThreadSafe;

    private static Map<Integer, String> mapNoThreadSafe;

    public static void main(String[] args) {
        final int quantityPrintExamples = 10;
        exampleList(quantityPrintExamples);
        exampleMap(quantityPrintExamples);
    }

    private static void exampleList(final int limit) {
        for (int i = 0; i < limit; i++) {
            listThreadSafe = new CopyOnWriteArrayList<>();
            listNoThreadSafe = new ArrayList<>();

            System.out.println("\n------ List - No Thread Safe ------");
            printListNoThreadSafe();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("------ List - Thread Safe ------");
            printListThreadSafe();
        }
    }

    private static void exampleMap(final int limit) {
        for (int i = 0; i < limit; i++) {
            mapThreadSafe = new ConcurrentHashMap<>();
            mapNoThreadSafe = new HashMap<>();

            System.out.println("\n------ MAP - No Thread Safe ------");
            printMapNoThreadSafe();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("------ MAP - Thread Safe ------");
            printMapThreadSafe();
        }
    }

    public static void printListNoThreadSafe() {
        Runnable runnable = () -> listNoThreadSafe.add("New Value in List");

        Thread threadOne = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);
        Thread threadThree = new Thread(runnable);

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Ocasionalmente, não irá imprimir todos os resultados
        System.out.println(listNoThreadSafe);
    }

    public static void printMapNoThreadSafe() {
        Runnable runnable = () -> mapNoThreadSafe.put(new Random().nextInt(), "New Value in Map");

        Thread threadOne = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);
        Thread threadThree = new Thread(runnable);

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Ocasionalmente, não irá imprimir todos os resultados
        System.out.println(mapNoThreadSafe);
    }

    private static void printListThreadSafe() {
        Runnable runnable = () -> listThreadSafe.add("New Value in List");

        Thread threadOne = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);
        Thread threadThree = new Thread(runnable);

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            threadOne.join();
            threadTwo.join();
            threadThree.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Irá imprimir todas as Inserções do Map
        System.out.println(listThreadSafe);
    }

    private static void printMapThreadSafe() {
        Runnable runnable = () -> mapThreadSafe.put(new Random().nextInt(), "New Value in Map");

        Thread threadOne = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);
        Thread threadThree = new Thread(runnable);

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            threadOne.join();
            threadTwo.join();
            threadThree.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Irá imprimir todas as Inserções do Map
        System.out.println(mapThreadSafe);
    }

}