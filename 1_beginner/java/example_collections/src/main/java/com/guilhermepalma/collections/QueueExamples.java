package com.guilhermepalma.collections;

import java.lang.reflect.Array;
import java.util.*;

public class QueueExamples {

    public static void main(String[] args) {
        exampleQueue();
    }

    private static void exampleQueue(){
        Queue<String> bankQueue = new LinkedList<>();
        bankQueue.add("Andersom");
        bankQueue.add("Juliana");
        bankQueue.add("Robson");
        bankQueue.add("Carolina");
        bankQueue.add("Alberto");

        System.out.println(bankQueue);

        // peek: Retorna, mas NÃO REMOVE o proximo elemento da Fila (Retorna null quando a fila estiver vazia)
        String initialClientWithoutRemove = bankQueue.peek();
        System.out.println("\nPrimeiro Item: " + initialClientWithoutRemove);
        System.out.println("Lista Original SEM ter o Item Removido: " + bankQueue);

        // poll: Retorna e REMOVE o Proximo elemento da Fila (Retorna null quando a fila estiver vazia)
        String initialClientWithRemove = bankQueue.poll();
        System.out.println("\nPrimeiro Item: " + initialClientWithRemove);
        System.out.println("Lista Original COM o Item Removido: " + bankQueue);

        // element: Retorna, mas NÃO REMOVE o proximo elemento da Fila (Retorna uma exception quando a fila estiver vazia)
        Queue<String> objectExample = bankQueue;
        try{
            objectExample.clear();
            objectExample.element();

            System.out.println("A execução não chegará aqui");
        }catch (NoSuchElementException ex){
            System.out.println("\nEssa Exceção ocorreu pela fila estar vazia e utilizar o metodo '.element()'");
        }


























    }
}
