package com.guilhermepalma.notify;


class TiqueTaque {

    private boolean isPrintTique;

    public synchronized void printTique(boolean executeTique) {
        if (!executeTique) {
            isPrintTique = true;
            notify();
            return;
        }

        System.out.print("Tique-");
        isPrintTique = true;
        notify();

        try {
            while (isPrintTique) wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void printTaque(boolean executeTaque) {
        if (!executeTaque) {
            isPrintTique = false;
            notify();
            return;
        }

        System.out.println("Taque");
        isPrintTique = false;
        notify();

        try {
            while (!isPrintTique) wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}