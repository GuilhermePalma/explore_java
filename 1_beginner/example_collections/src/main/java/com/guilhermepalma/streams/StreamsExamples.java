package com.guilhermepalma.streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsExamples {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Andersom");
        names.add("Juliana");
        names.add("Robson");
        names.add("Carolina");
        names.add("Alberton");

        // Imprime a Lista Inteira, na ordem atual
        System.out.println(names);

        // Obtem a quantidade de elementos do Stream
        long countList = names.stream().count();
        System.out.println("\nContagem da Lista: " + countList);

        // Obtem a maior e menor String da Lis por meio do Uso de Comparators e Stream
        String stringWithMaxLength = names.stream().max(Comparator.comparingInt(String::length)).orElse(null);
        System.out.println("\nMaior String da Lista: " + stringWithMaxLength);
        String stringWithMinLength = names.stream().min(Comparator.comparingInt(String::length)).orElse(null);
        System.out.println("Menor String da Lista: " + stringWithMinLength);

        // .filter: Metodo do Stream que recebe um metodo que retorna true/false, filtrando os resultados
        // .collect: Metodo do Stream que transforma o resultado do Stream em uma nova Collection (ex: List)
        List<String> namesWithMoreSevenChars = names.stream().filter((value) -> value.length() > 6).collect(Collectors.toList());
        System.out.println("\n" + namesWithMoreSevenChars);
        List<String> namesWithLetterO = names.stream().filter((value) -> value.toLowerCase().contains("o")).collect(Collectors.toList());
        System.out.println(namesWithLetterO);


        // .map: Retorna a mesma quantidade de itens da coleção, mas transforma os dados conforme o bloco de codigo
        List<String> valuesConcat = names.stream().map(value -> "Nome: ".concat(value).concat(" na Lista !")).collect(Collectors.toList());
        System.out.println("\n" + valuesConcat);

        // .limit: Limita a quantidade de Elementos que serão obtidos
        List<String> twoElements = names.stream().limit(2L).collect(Collectors.toList());
        System.out.println("\n" + twoElements);

        // .peek: Executa um trecho de codigo para cada elemento e retorna eles
        System.out.println("\n");
        List<String> listWithSameValues = names.stream().peek(item -> System.out.println("(PEEK): Nome: " + item)).collect(Collectors.toList());
        System.out.println("\n" + listWithSameValues);

        // .forEach: Executa um trecho de codigo e NÃO RETORNA os elementos
        System.out.println("\n");
        names.stream().forEach(item -> System.out.println("(ForEach): Nome: " + item));

        // .allMatch: Verifica se TODOS os elementos atendem à uma condição
        boolean allItensContainsN = names.stream().allMatch((values) -> values.toLowerCase().contains("n"));
        System.out.println("\nTodos os Elementos Possuem \"n\" ? " + allItensContainsN);
        boolean allItensContainsZ = names.stream().allMatch((values) -> values.toLowerCase().contains("z"));
        System.out.println("\nTodos os Elementos Possuem \"z\" ? " + allItensContainsZ);

        // .anyMatch: Verifica se APENAS UM dos elementos atendem à uma condição
        boolean itensContainsUpperA = names.stream().allMatch((values) -> values.contains("A"));
        System.out.println("\nAlgum Elemento possui \"A\" ? " + itensContainsUpperA);

        // .noneMatch: Verifica se TODOS os elementos NÃO atendem à uma condição
        boolean itensNotContainsUpperP = names.stream().noneMatch((values) -> values.contains("P"));
        System.out.println("\nTodos os Elementos não Possuem \"P\" ? " + itensNotContainsUpperP);

        // .findFirst: Obtem o Primeiro Item da Collection (Retorna um Optional ou um valor pre-definido pelo .orElse)
        String firstItem = names.stream().findFirst().orElse(null);
        System.out.println("\nPrimeiro Item: " + firstItem);

        // Operações Encadeadas: Maioria dos metodos do Stream retornam uma instancia de Stream, permitindo realizar + operações
        List<String> distincOperations = names.stream()
                .limit(3L)
                .filter((valueOne) -> valueOne.toLowerCase().contains("a"))
                .map((valueTwo) -> String.format("Nome: [%s]", valueTwo))
                .collect(Collectors.toList());
        System.out.println("\n" + distincOperations);

        // Insere um dado já existente na lista e a converte em um set (valores unicos)
        names.add("Robson");
        Set<String> uniqueNames = names.stream().collect(Collectors.toSet());
        System.out.println("\n" + uniqueNames);

        // Exemplo de Join que separa os valores com um delimitador (ex: virgula)
        String uniqueStringWithValues = uniqueNames.stream().collect((Collectors.joining(", ")));
        System.out.println("\n" + uniqueStringWithValues);

        // Exemplo de Operações Paraleas
        System.out.println("\n");
        System.out.println(names.stream()
                .peek(v -> System.out.println("\nItem antes de Executar o Map: " + v))
                .map(v -> v.concat(" (Nova String após no Map)"))
                .peek(System.out::println)
                .collect(Collectors.toList())
        );

        List<String> listWithRandomNumbers = names.stream()
                .map((value) -> value.concat(" - ").concat(String.valueOf(new Random().nextInt(2))))
                .collect(Collectors.toList());
        Map<Object, List<String>> groupedNumbersList = listWithRandomNumbers.stream().collect(Collectors.groupingBy((value) -> value.substring(value.indexOf("-") + 1)));
        System.out.println("\n" + groupedNumbersList);
    }

}