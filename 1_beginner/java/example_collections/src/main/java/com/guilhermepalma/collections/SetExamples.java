package com.guilhermepalma.collections;

import java.util.*;

public class SetExamples {
    public static void main(String[] args) {
        String[] types = new String[]{"HASH_SET", "LINKED_HASH_SET"};
        for (String value : types) {
            Set<Double> notes = new LinkedHashSet<>();
            if (value.equals("HASH_SET")) {
                notes = new HashSet<>(Arrays.asList(5.0, 3.4, 2.9, 58023.324, 1.2));

                // A ordem dos intens NÃO SERA mantida (Será ordenado pela ordem dos Numeros)
                System.out.println("\nHashSet");
                System.out.println(notes);
            } else if (value.equals("LINKED_HASH_SET")) {
                notes = new HashSet<>(Arrays.asList(3.4, 2.9, 58023.324, 1.2, 5.0));

                // A ordem dos intens SERA mantida (Será ordenado pela ordem dos Numeros)
                System.out.println("\nLinkedHashSet");
                System.out.println(notes);
            }

            comunMethods(notes);
        }

        System.out.println("\nTreeSet");
        TreeSet<String> cities = new TreeSet<>(Arrays.asList("Rio de Janeiro", "São Paulo", "Amapa", "Recife", "Palmas"));

        // Ordem de Inserção NÃO SERA mantida
        System.out.println(cities);

        // Obtem o primeiro item
        String firstCity= cities.first();
        System.out.println("Primeiro Item: " + firstCity);

        // Obtem o utlimo item
        String lastCity= cities.last();
        System.out.println("Ultimo Item: " + lastCity);

        // Elemento acima de Rio de Janeiro
        String elementHigherOfRJ = cities.higher("Rio de Janeiro");
        System.out.println("Elemento acima de Rio de Janeiro: " + elementHigherOfRJ);

        // Elemento abaixo de Rio de Janeiro
        String elementLoweOfRJ = cities.lower("Rio de Janeiro");
        System.out.println("Elemento abaixo de Rio de Janeiro: " + elementLoweOfRJ);

        // Item não existente na Lista = Pega o elemento acima do Primeiro
        String elementNotExistsFirst = cities.higher("Lorarwk Osls 12");
        System.out.println("Elemento não Existe = " + elementNotExistsFirst);

        // Item não existente na Lista = Pega o Primeiro Elemento
        String elementNotExistsLast = cities.lower("Lorarwk Osls 12");
        System.out.println("Elemento não Existe = " + elementNotExistsLast);

        // Obtem o Primeiro item e remove-o do Set
        String firstCityWithPoll = cities.pollFirst();
        System.out.println("Primeiro Item retirando do Set: " + firstCityWithPoll);

        // Obtem o Ultimo item e remove-o do Set
        String lastCityWithPoll = cities.pollLast();
        System.out.println("Ultimo Item retirando do Set: " + lastCityWithPoll);

        // Set após a retirda dos primeiro e ultimo item
        System.out.println(cities);
    }

    private static void comunMethods(Set<Double> setValues) {
        // Remove um Item da Lista
        setValues.remove(5.0);
        System.out.println(setValues);

        // Não irá remover pois o dado  nao existe
        setValues.remove(50.0);

        setValues.forEach(System.out::println);

        boolean isNotEmpty = setValues.isEmpty();
        System.out.println("Set está vazia ? " + isNotEmpty);

        // Esvazia a Lista
        setValues.clear();
        boolean isEmpty = setValues.isEmpty();
        System.out.println("Set está vazia depois do \".clear()\" ? " + isEmpty);
    }


}
