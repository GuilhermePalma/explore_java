package com.guilhermepalma.appCalculate;

import com.guilhermepalma.appCalculate.intern.MathOperations;

/**
 * Classe de Calcula compartilhada com outras Aplicações
 */
public class Calculate {

    private final MathOperations mathOperations = new MathOperations();

    /**
     * Metodo Delegado que executa um Metodo interno de {@link MathOperations}
     */
    public double sum(double... numbers) {
        return mathOperations.sum(numbers);
    }
}
