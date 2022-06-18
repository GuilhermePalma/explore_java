package com.guilhermepalma.comparators;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorListExample {

    public static void main(String[] args) {
        final Student[] studentList = new Student[]{new Student("Gabriel", 10), new Student("Joao", 32), new Student("Amanda", 10), new Student("Raue", 50), new Student("Luis", 20)};

        // Apresenta na Ordem de Inserção dos Itens
        System.out.println(Arrays.toString(studentList));

        // Ordena por Lambda
        List<Student> sortByLambda = Arrays.asList(studentList);
        System.out.println("\nOrdenação por Lambda (JAVA 8)");

        // Organiza por o Ordem Crescente da Idade
        sortByLambda.sort((first, second) -> first.getAge() - second.getAge());
        System.out.println(sortByLambda);

        // Organiza por o Ordem Decrescente da Idade
        sortByLambda.sort((first, second) -> second.getAge() - first.getAge());
        System.out.println(sortByLambda);

        // Organiza por o Ordem Crescente da Idade pelo Lambda
        sortByLambda.sort(Comparator.comparingInt(Student::getAge));
        System.out.println(sortByLambda);

        // Organiza por o Ordem decrescente da Idade pelo Lambda
        sortByLambda.sort(Comparator.comparingInt(Student::getAge).reversed());
        System.out.println(sortByLambda);


        System.out.println("\nCollection Methods");
        List<Student> sortCollection = Arrays.asList(studentList);

        // Somente é possivel utilizar esse metodo quando a Classe possui o "implements Comparable"
        Collections.sort(sortCollection);
        System.out.println(sortCollection);

        // O Valor passado no Sort deve OBRIGATORIAMENTE implementar o Camparator, mas a Classe da Lista não precisa
        sortCollection.sort(new StudentReverseAgeComparator());
        // Usando a CollectionsCollections.sort(sortCollection, new StudentReverseAgeComparator());
        System.out.println(sortCollection);

    }

}


/**
 * Classe de Dominio (Model) que implementa um {@link Comparable} para realziar a comparação entre o Objeto atual e um Objeto recebido
 */
class Student implements Comparable<Student> {
    private final String name;
    private final Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    /**
     * Realiza a comparação entre o objeto atuale o objeto recebidopara fazer a Ordenação
     * <p>
     * Numero Positivo: Objeto atual é MAIOR que o Objeto comparado
     * <p>
     * Numero Negativo: Objeto atual é MENOR que o Objeto comparado
     * <p>
     * Valor igual a 0: Os valores comparados são IGUAIS
     *
     * @return int
     **/
    @Override
    public int compareTo(Student o) {
        return this.getAge() - o.getAge();
    }

    @Override
    public String toString() {
        Field[] fieldsArray = this.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (Field field : fieldsArray) {
            try {
                String key = field.getName();
                Object value = field.get(this);
                stringBuilder.append(key).append(": ").append(value).append(",");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        stringBuilder.reverse();
        stringBuilder.replace(0, 1, "");
        stringBuilder.reverse();

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}

/**
 * Nessa classe, implementa o {@link  Comparator} para realizar a comparação entre dois objetos recebidos
 */
class StudentReverseAgeComparator implements Comparator<Student> {

    /**
     * Realiza a comparação entre objetos dois objetos para fazer a Ordenação
     * <p>
     * Numero Positivo: Objeto atual é MAIOR que o Objeto comparado
     * <p>
     * Numero Negativo: Objeto atual é MENOR que o Objeto comparado
     * <p>
     * Valor igual a 0: Os valores comparados são IGUAIS
     *
     * @return int
     **/
    @Override
    public int compare(Student o1, Student o2) {
        return o2.getAge() - o1.getAge();
    }
}