package com.guilhermepalma.syncronized;

import java.util.concurrent.atomic.AtomicInteger;

class Table {

    /**
     * Metodo responsavel por retornar uma Tabela com as multiplicações de um número até um certo limite. Por ser um
     * metodo sincronizado, é Thread-safe, ou seja, só será possivel acessar uma instância da classe por Thread, fazendo
     * com que as Threads trabalhem e utilizem Objetos diferentes, para não gerar inconformidades
     *
     * @param number   Numero que será multiplicado
     * @param quantity Ultimo Multiplicador que o Number pode ser multiplicado
     * @see #formatLine(int, int, int)
     */
    public synchronized void printTable(int number, int quantity) {
        int sizeLine = calcSizeLine(number, quantity);

        System.out.println(generateHeader("Table", sizeLine));
        for (int i = 1; i <= quantity; i++) {
            try {
                System.out.println(formatLine(number, i, sizeLine));
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(generateFooter(sizeLine));
    }

    /**
     * Metodo responsavel por Retornar a Linha Formatada com os numeros e valores
     *
     * @param number   Numero que será multiplicado
     * @param line     Multiplicador do Numero
     * @param sizeLine Tamanho da maior da Linha com os valores e resultados que existe na tabela
     * @return {@link String}
     */
    private String formatLine(int number, int line, int sizeLine) {
        StringBuilder string = new StringBuilder(String.format("|[%d] X [%d] = [%d]", number, line, number * line));
        int whiteSpace = sizeLine - 1;

        AtomicInteger whiteSpace1 = new AtomicInteger(string.length());
        while (whiteSpace1.getAndIncrement() < whiteSpace) {
            string.append(" ");
        }
        string.append("|");
        return string.toString();
    }

    /**
     * Metodo responsavel por Calcular o Tamanho da Linha da Tabela
     *
     * @param number   Numero que será multiplicado
     * @param quantity Ultimo Multiplicador aplicado
     * @return int
     */
    private int calcSizeLine(int number, int quantity) {
        return 14 + String.valueOf(number).length() + String.valueOf(quantity).length() + String.valueOf(quantity * number).length();
    }

    /**
     * Metodo responsavel por gerar o Header da Tabela
     *
     * @param textInHeader Texto que será exibido no Header
     * @param sizeLine     Tamanho da maior da Linha com os valores e resultados que existe na tabela
     * @return {@link String}
     */
    private String generateHeader(String textInHeader, int sizeLine) {
        sizeLine -= textInHeader.length();
        int middle = sizeLine % 2 == 0 ? sizeLine / 2 : (sizeLine + 1) / 2;

        StringBuilder formatHeader = new StringBuilder("\n");
        for (int i = 0; i <= sizeLine; i++) {
            if (middle == i) formatHeader.append(textInHeader);
            else formatHeader.append("-");
        }

        return formatHeader.toString();
    }

    /**
     * Metodo responsavel por gerar o Footer da Tabela
     *
     * @param sizeLine Tamanho da maior da Linha com os valores e resultados que existe na tabela
     * @return {@link String}
     */
    private String generateFooter(int sizeLine) {
        StringBuilder textHeader = new StringBuilder();
        for (int i = 0; i < sizeLine; i++) {
            textHeader.append("-");
        }
        return textHeader.toString();
    }

}
