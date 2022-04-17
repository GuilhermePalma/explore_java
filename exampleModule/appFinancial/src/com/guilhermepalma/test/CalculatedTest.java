package com.guilhermepalma.test;

import com.guilhermepalma.appCalculate.Calculate;

/**
 * Classe de Teste para os Metodos da Classe {@link Calculate} que é compartilhada entre as aplicações
 */
public class CalculatedTest {
    public static void main(String[] args) {
        // Acessa a Classe "Calculate" do Projeto appCalculate
        Calculate calculate = new Calculate();
        System.out.println(calculate.sum(3, 5, 9));
    }
}
