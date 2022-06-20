package com.guilhermepalma.collections;

import java.util.*;

public class ListExamples {
    public static void main(String[] args) {
        arrayListExample();
    }

    private static void arrayListExample() {
        List<String> names = new ArrayList<>();
        names.add("Andersom");
        names.add("Juliana");
        names.add("Robson");
        names.add("Carolina");
        names.add("Alberto");

        // Imprime a Lista Inteira, na ordem atual
        System.out.println(names);

        // Posições de um Objeto na Lista
        int indexJuliana = names.indexOf("Juliana");
        System.out.println("\nJuliana está na Posição: " + indexJuliana);

        // Quando o Elemento não está na Lista, o index retornado é -1
        int indexNotFound = names.indexOf("Lais");
        System.out.println("\nLais não existe na Lista, logo, o index é: " + indexNotFound);

        // Atribui um novo valor na posição 3
        names.set(3, "Juan");

        // Remove um Item da Lista (Pelo valor e Pelo Index)
        names.remove("Juan");
        names.remove(2);

        // Obtem o nome conforme o Index
        String firstName = names.get(0);
        System.out.println("\nItem na Primeira Posição: " + firstName);

        // Obtem o Tamanho da Lista
        int sizeOfList = names.size();
        System.out.println("\nTamanho da Lista: " +sizeOfList);

        // Operações que podem gerar Exceções na Lista
        try {
            String moreOfSize = names.get(10);
            String negativeValue = names.get(-1);

            System.out.println("A Execução não chegará aqui, pois ocorre uma Exception na Primeira linha do try/catch");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("\nNão é possivel obter posições negativas de uma Lista. Tambem não é possivel obter um indice maior que o da Lista (10)");
        }

        // Ordena os nomes, modificando as posições na Ordem Alfabetica
        Collections.sort(names);
        System.out.println(names);

        // Verifica se Possui os Elementos na Lista
        boolean hasFabio = names.contains("Fabio");
        boolean hasAndersom = names.contains("Andersom");
        System.out.printf("A Lista Possui '[%s]' ?: [%s]%n", "Fabio", hasFabio);
        System.out.printf("A Lista Possui '[%s]' ?: [%s]%n%n", "Andersom", hasAndersom);

        // Metodo Imperativo (Ordem explicita do Programador) para cada item da Lista
        for (String name : names) {
            System.out.printf("(Struct FOR EACH) Nome: [%s] - Index: [%s]%n", name, name.indexOf(name));
        }

        // A utilização do Iterator permite operações com cada item de forma mais Declarativa
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            // Será executado enquanto existir uma proxima posição, pegando o proximo item
            System.out.println("(ITERATOR) Nome: " + iterator.next());
        }

        // Limpa conteudo da Lista
        names.clear();
        System.out.println("\nTamanho da Lista após Limpa-la: " + names.size());
    }

}