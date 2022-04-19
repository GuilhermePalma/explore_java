package com.guilhermepalma.examplespringbot.controllers;

import com.guilhermepalma.examplespringbot.models.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/clients") // Todas os Metodos desse Controller será mapeado com o prefixo "/clients"
public class ClientController {

    private static final String alphabethic = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @GetMapping(path = "/any")
    public Client getRandomClient() {
        Random randomClass = new Random();

        // Obtem um ID Inteiro Positivo
        int randomId = randomClass.nextInt();
        if (randomId < 0) randomId *= -1;

        // Obtem uma Sequencia de Letras
        StringBuilder randomName = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            int randomPosition = randomClass.nextInt(26);

            if (randomPosition == 0) randomName.append(alphabethic, 0, 0);
            else randomName.append(alphabethic, (randomPosition - 1), randomPosition);
        }

        // Formatação das Letras Maiusculas e Minusculas
        String formattedName = randomName.charAt(0) +
                randomName.replace(0, 1, "").toString().toLowerCase();

        // Gera a Trinca de Numeros Aleatorios do CPF
        StringBuilder randomCPF = new StringBuilder();
        for (int i = 0; i < 3; i++) randomCPF.append(randomClass.nextInt(999)).append('.');

        // Gera os Dois ultimos Numeros do CPF
        randomCPF.replace((randomCPF.length() - 1), randomCPF.length(), "-")
                .append(randomClass.nextInt(99));

        // Retorna o Registro Aleatorio
        return new Client(randomId, formattedName, randomCPF.toString());
    }

}
