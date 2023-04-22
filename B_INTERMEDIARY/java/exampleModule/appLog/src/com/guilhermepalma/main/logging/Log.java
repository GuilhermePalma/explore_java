package com.guilhermepalma.main.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que Imprime uma Mensagem no Console/Terminal
 */
public class Log {
    public static void printInformation(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("[INFO] " + simpleDateFormat.format(new Date()) + ": " + message);
    }
}
