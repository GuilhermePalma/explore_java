package com.guilhermepalma.notify;

public class NotifyExamples {

    public static void main(String[] args) {
        TiqueTaque tt = new TiqueTaque();

        Runnable runnableTique = () -> {
            for (int i = 0; i < 4; i++) tt.printTique(true);
            tt.printTique(false);
        };

        Runnable runnableTaque = () -> {
            for (int i = 0; i < 4; i++) tt.printTaque(true);
            tt.printTaque(false);
        };

        new Thread(runnableTique).start();
        new Thread(runnableTaque).start();
    }

}
