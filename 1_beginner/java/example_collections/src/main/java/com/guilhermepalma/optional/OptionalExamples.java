package com.guilhermepalma.optional;

import java.util.*;

public class OptionalExamples {
    public static void main(String[] args) {
        overviewOption();
        optionalNative();
    }

    private static void overviewOption() {
        // Cria um Optional vazio (.empty)
        Optional<String> name = Optional.empty();
        System.out.println(name.isPresent()); // false = Optional vazio

        try {
            String generationException = name.get();
        }catch (NoSuchElementException ex){
            System.out.println("Esse bloco irá gerar uma Exceção. O metodo GET retorna o valor (caso presente)" +
                    " ou a Exceção \"NoSuchElementException\" caso o valor não exista ou seja \"null\"");
        }

        try {
            name.orElseThrow(IllegalAccessError::new);
        }catch (IllegalAccessError ex){
            System.out.println("Esse bloco irá gerar uma Exceção. O metodo \"orElseThrow\" retorna uma Exception" +
                    " definida caso o valor seja vazio ou nulo");
        }

        // Recebe um valor que pode ser nulo (ofNullable)
        String nullString  = null;
        name = Optional.ofNullable(nullString);
        System.out.println(name.isPresent()); // false = Optional recebendo valor nulo

        // Retorna um valor padrão para caso o Option seja nulo/sem valor (Não será executado)
        String originalValueOne = name.orElse("STRING_VAZIA");
        System.out.println(originalValueOne.equals("STRING_VAZIA")); // true: Optional sem valor = Recebe o outro valor

        // Somente executa se o Option tiver algum valor (nesse caso, não será executado)
        name.ifPresent((item) -> System.out.println("Não Executa: Só será executado se o valor estiver Presente"));

        // Recebe um valor NÃO NULO (of)
        name = Optional.of("value");
        System.out.println(name.isPresent()); // true = Optional recebendo um valor

        name.ifPresent((item) -> System.out.println("Executado: Só será executado se o valor estiver Presente"));

        // Retorna um valor padrão para caso o Option seja nulo/sem valor (Não será executado)
        String originalValueTwo = name.orElse("STRING_VAZIA");
        System.out.println(originalValueTwo.equals("STRING_VAZIA")); // false: Optional com valor = retorna o proprio valor

        // Antes de usar o Map é importante verificar se é diferente de null para não gerar NullPointerException
        if(name.isPresent()){
            // Retorna um valor transformado
            name.map((value) -> value.concat("[Trecho da String Adicionado]"));
        }

    }

    private static void optionalNative(){
        // Implementações nativas e explicitas do Option

        OptionalInt.of(3).ifPresent(value -> System.out.printf("Valor [%s] presente%n", value));
        OptionalInt.empty().ifPresent(value -> System.out.println("Não será executado"));

        OptionalLong.of(2233L).ifPresent(value -> System.out.printf("Valor [%s] presente%n", value));
        OptionalLong.empty().ifPresent(value -> System.out.println("Não será executado"));

        OptionalDouble.of(412.32D).ifPresent(value -> System.out.printf("Valor [%s] presente%n", value));
        OptionalDouble.empty().ifPresent(value -> System.out.println("Não será executado"));

    }
}
