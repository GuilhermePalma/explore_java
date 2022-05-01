package com.guilhermepalma.simpleRequestAPI.models;

import java.util.Random;

public class Client {

    private static final String alphabethic = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private int id;
    private String name;
    private String cpf;

    public Client(int id, String name, String cpf) {
        super();
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRandomCpf() {
        Random randomClass = new Random();

        // Gera a Trinca de Numeros Aleatorios do CPF
        StringBuilder randomCPF = new StringBuilder();
        for (int i = 0; i < 3; i++) randomCPF.append(randomClass.nextInt(999)).append('.');

        // Gera os Dois ultimos Numeros do CPF
        randomCPF.replace((randomCPF.length() - 1), randomCPF.length(), "-")
                .append(randomClass.nextInt(99));
    }

    public void setRandomName() {
        Random randomClass = new Random();

        // Obtem uma Sequencia de Letras
        StringBuilder randomName = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            int randomPosition = randomClass.nextInt(26);

            if (randomPosition == 0) randomName.append(alphabethic, 0, 0);
            else randomName.append(alphabethic, (randomPosition - 1), randomPosition);
        }

        // Formatação das Letras Maiusculas e Minusculas
        this.setName(randomName.charAt(0) + randomName.replace(0, 1, "")
                .toString().toLowerCase());
    }
}
