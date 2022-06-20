package com.guilhermepalma.test;

import com.guilhermepalma.appCalculate.Calculate;
import com.guilhermepalma.appCalculate.intern.MathOperations;

/**
 * Classe de Teste para os Metodos da Classe {@link Calculate} que é compartilhada entre as aplicações
 */
public class CalculatedTest {
    public static void main(String[] args) {
        calculateSimple();
        calculateIntern();
    }

    /**
     * Utiliza da Classe {@link Calculate} do Modulo "appCalculate"
     */
    private static void calculateSimple() {
        // Acessa a Classe "Calculate" do Projeto appCalculate para Calcular
        Calculate calculate = new Calculate();

        // Acessa a Classe "Logger" do Projeto appLog para Imprimir o Resultado
        calculate.printMessage(String.valueOf(calculate.sum(3, 5, 9)));
    }

    /**
     * O appCalculte permitiu que fosse acessado os modulos Internos (MathOperations)
     */
    private static void calculateIntern() {
        MathOperations mathOperations = new MathOperations();
        System.out.println(mathOperations.sum(8, 30, 6.5));
    }
}
