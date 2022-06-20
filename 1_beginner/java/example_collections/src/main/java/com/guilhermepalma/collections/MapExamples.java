package com.guilhermepalma.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapExamples {

    public static void main(String[] args) {
        System.out.println("HashMap");
        exampleHashMap();

        System.out.println("\nTreeMap");
        exampleTreeMap();
    }

    public static void exampleHashMap() {

        // Map com as Chaves String e Valores Double
        Map<String, Double> notes = new HashMap<>();

        // Metodo Responsavel por inserir uma key-value no Map
        notes.put("Juliana", 8.5);
        notes.put("Gabriel", 10.0);
        notes.put("Felipe", 4.54);
        notes.put("Guilherme", 4.6);
        notes.put("Kaua", 6.5);

        // A ordem de inserção SERA mantida
        System.out.println(notes);

        // Não irá dar um erro ao dar .put em um valor já existente, mas sim reescreve-lo
        notes.put("Kaua", 8.3);
        System.out.println(notes);

        // Obtem um Registro
        Double kauaValue = notes.get("Kaua");
        System.out.println(kauaValue);

        boolean containsKeyKaua = notes.containsKey("Kaua");
        System.out.println("O Map Possui um Registro com a Key \"Kaua\"? " + containsKeyKaua);

        boolean containsValue10 = notes.containsValue(10.0);
        System.out.println("O Map Possui um Valor com o valor de \"10.0\"? " + containsValue10);

        // Remove o Item Kaua
        notes.remove("Kaua");
        System.out.println(notes);

        int size = notes.size();
        System.out.println("Tamanho do Map: " + size);

        // Obtem os Valores no Formato de Set
        Set<Map.Entry<String, Double>> valuesSet = notes.entrySet();
        for (Map.Entry<String, Double> value : valuesSet) {
            System.out.printf("Valor da Key [%s]: [%s]%n", value.getKey(), value.getValue());
        }

        // Obtem as Keys do Map, convertendo em Set (Já que são Unicas)
        Set<String> keySet = notes.keySet();
        for (String key : keySet) {
            System.out.println("Chave do Map: " + key);
        }


        notes.clear();
        int sizeAfterClear = notes.size();
        System.out.println("Tamanho do Map Após o .clear: " + sizeAfterClear);
    }

    public static void exampleTreeMap() {
        // Map com as Chaves String e Valores Double
        TreeMap<String, Double> notes = new TreeMap<>();

        // Metodo Responsavel por inserir uma key-value no Map
        notes.put("Juliana", 8.5);
        notes.put("Gabriel", 10.0);
        notes.put("Felipe", 4.54);
        notes.put("Guilherme", 4.6);
        notes.put("Kaua", 6.5);

        // A ordem de inserção NAO SERA mantida
        System.out.println(notes);

        // Obtem a Primeira Chave e o Primeiro Valor
        String firstKey = notes.firstKey();
        Double firstValue = notes.firstEntry().getValue();
        System.out.printf("Valor da Primeira Chave ([%s]): [%s]%n", firstKey, firstValue);

        // Obtem a Ultima Chave e o Ultimo Valor
        String lastKey = notes.firstKey();
        Double lastValue = notes.lastEntry().getValue();
        System.out.printf("Valor da Utima Chave ([%s]): [%s]%n", lastKey, lastValue);

        // Obtem o Item acima de Guilherme
        Map.Entry<String, Double> elementHigherGui=  notes.higherEntry("Guilherme");
        System.out.printf("Valor do Item acima do \"Guilherme\": [%s]: [%s]%n", elementHigherGui.getKey(), elementHigherGui.getValue());

        // Obtem os Item abaixo de Guilherme
        Map.Entry<String, Double> elementLowerGui=  notes.lowerEntry("Guilherme");
        System.out.printf("Valor do Item acima do \"Guilherme\": [%s]: [%s]%n", elementLowerGui.getKey(), elementLowerGui.getValue());


        // Obtem o Primeiro Item e retira-o do Map de Guilherme
        Map.Entry<String, Double> firstElement=  notes.pollFirstEntry();
        System.out.printf("Valor do Primeiro Item: [%s]: [%s]%n", firstElement.getKey(), firstElement.getValue());

        // Obtem o Ultimo Item e retira-o do Map de Guilherme
        Map.Entry<String, Double> lastEntryElement=  notes.pollLastEntry();
        System.out.printf("Valor do Ultimo Item: [%s]: [%s]%n", lastEntryElement.getKey(), lastEntryElement.getValue());

        // Não irá dar um erro ao dar .put em um valor já existente, mas sim reescreve-lo
        notes.put("Kaua", 8.3);
        System.out.println(notes);

        // Obtem um Registro
        Double kauaValue = notes.get("Kaua");
        System.out.println(kauaValue);

        boolean containsKeyKaua = notes.containsKey("Kaua");
        System.out.println("O Map Possui um Registro com a Key \"Kaua\"? " + containsKeyKaua);

        boolean containsValue10 = notes.containsValue(10.0);
        System.out.println("O Map Possui um Valor com o valor de \"10.0\"? " + containsValue10);

        // Remove o Item Kaua
        notes.remove("Kaua");
        System.out.println(notes);

        int size = notes.size();
        System.out.println("Tamanho do Map: " + size);

        // Obtem os Valores no Formato de Set
        Set<Map.Entry<String, Double>> valuesSet = notes.entrySet();
        for (Map.Entry<String, Double> value : valuesSet) {
            System.out.printf("Valor da Key [%s]: [%s]%n", value.getKey(), value.getValue());
        }

        // Obtem as Keys do Map, convertendo em Set (Já que são Unicas)
        Set<String> keySet = notes.keySet();
        for (String key : keySet) {
            System.out.println("Chave do Map: " + key);
        }


        notes.clear();
        int sizeAfterClear = notes.size();
        System.out.println("Tamanho do Map Após o .clear: " + sizeAfterClear);
    }
}
