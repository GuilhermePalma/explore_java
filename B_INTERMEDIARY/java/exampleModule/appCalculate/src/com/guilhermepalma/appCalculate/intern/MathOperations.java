package com.guilhermepalma.appCalculate.intern;

import java.util.Arrays;

/**
 * Classe Interna da Aplicação appCalcultate que não será compartilhada com as Outras Aplicações
 */
public class MathOperations {

    /**
     * Retorna a Soma de um Conjunto de Numeros
     */
    public double sum(double... numbers) {
        return Arrays.stream(numbers).reduce(0.0, (total, actual) -> total + actual);
    }

}
