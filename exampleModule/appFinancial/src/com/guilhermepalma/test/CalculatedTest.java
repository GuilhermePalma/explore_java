package com.guilhermepalma.test;

import com.guilhermepalma.appCalculate.Calculate;

/**
 * Classe de Teste para os Metodos da Classe {@link Calculate} que é compartilhada entre as aplicações
 */
public class CalculatedTest {
    public static void main(String[] args) {
        // Acessa a Classe "Calculate" do Projeto appCalculate para Calcular
        Calculate calculate = new Calculate();

        // Acessa a Classe "Logger" do Projeto appLog para Imprimir o Resultado
        calculate.printMessage(String.valueOf(calculate.sum(3, 5, 9)));
    }
}
