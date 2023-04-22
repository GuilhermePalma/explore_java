package com.guilhermepalma.deadlock;

public class DeadlockExample {

    private static final String RESOURCE_ONE = "Recurso #1";
    private static final String RESOURCE_TWO = "Recurso #2";


    public static void main(String[] args) {
        // Descomentando a Linha Abaixo, o Programa terá um deadlock e não terminará a execuação (loop)
        // exampleDeadlock();
        solvedDeadlock();
    }

    private static void solvedDeadlock() {
        PrintResources printResources = new PrintResources();

        Runnable runnableOne = () -> {
            printResources.printResourceOne("Runnable One");

            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            printResources.printResourceTwo("Runnable One");
        };

        Runnable runnableTwo = () -> {
            printResources.printResourceOne("Runnable Two");

            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            printResources.printResourceTwo("Runnable Two");
        };

        // O programa ficará num Loop infinito, pois não conseguirão acessar os últimos "RESOUCES"
        new Thread(runnableOne).start();
        new Thread(runnableTwo).start();
    }

    private static void exampleDeadlock() {
        Runnable runnableOne = () -> {
            synchronized (RESOURCE_ONE) {
                // Faz o bloqueio do "RESOURCE_ONE" para a utilização nessa Thread
                System.out.println("RunnableOne bloqueou o \"RESOURCE_ONE\"");

                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("RunnableOne tentando acessar o \"RESOURCE_TWO\"");

                synchronized (RESOURCE_TWO) {
                    // NÃO será possivel acessar o "RESOURCE_TWO" pois o runnableTwo está usando
                    System.out.println("RunnableOne bloqueou o \"RESOURCE_TWO\"");
                }
            }
        };

        Runnable runnableTwo = () -> {
            synchronized (RESOURCE_TWO) {
                // Faz o bloqueio do "RESOURCE_ONE" para a utilização nessa Thread
                System.out.println("RunnableTwo bloqueou o \"RESOURCE_TWO\"");

                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("RunnableTwo tentando acessar o \"RESOURCE_ONE\"");

                synchronized (RESOURCE_ONE) {
                    // NÃO será possivel acessar o "RESOURCE_ONE" pois o runnableOne está usando
                    System.out.println("RunnableTwo bloqueou o \"RESOURCE_ONE\"");
                }
            }
        };

        // O programa ficará num Loop infinito, pois não conseguirão acessar os últimos "RESOUCES"
        new Thread(runnableOne).start();
        new Thread(runnableTwo).start();
    }
}
