package com.guilhermepalma.syncronized;

public class SyncronizedThreads {
    // Instancia estatica na classe do Objeto Table que será utilizado em diferentes Threds
    private static final Table table = new Table();

    public static void main(String[] args) {
        // Utilização do Objeto em Diferetes Threads. Como foi utilizado o syncronized, o objeto só manterá os dados e
        // o estado gerado quando estiver na propria Thread. Já quando alterar a Thread, outro objeto Table será usado
        new Thread(() -> table.printTable(5, 3)).start();
        new Thread(() -> table.printTable(5, 80)).start();
        new Thread(() -> table.printTable(100, 7)).start();
    }
}