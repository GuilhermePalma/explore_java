package com.guilhermepalma.deadlock;


class PrintResources {

    boolean isAvalaibleResourceOne;

    public synchronized void printResourceOne(String name) {
        isAvalaibleResourceOne = false;
        notify();

        System.out.println(name + " - Recurso #1");

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        isAvalaibleResourceOne = true;
        notify();
    }

    public synchronized void printResourceTwo(String name) {
        isAvalaibleResourceOne = true;
        notify();

        System.out.println(name + " - Recurso #2");

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        isAvalaibleResourceOne = false;
        notify();
    }


}