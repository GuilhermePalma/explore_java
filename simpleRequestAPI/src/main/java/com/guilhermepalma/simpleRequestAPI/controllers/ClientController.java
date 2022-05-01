package com.guilhermepalma.simpleRequestAPI.controllers;

import com.guilhermepalma.simpleRequestAPI.models.Client;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/clients") // Todas os Metodos desse Controller será mapeado com o prefixo "/clients"
public class ClientController {


    @GetMapping(path = "/any")
    public Client getRandomClient() {
        // Obtem um ID Inteiro Positivo
        int randomId = new Random().nextInt();
        if (randomId < 0) randomId *= -1;

        Client client = new Client();
        client.setRandomCpf();
        client.setRandomName();
        client.setId(randomId);

        // Retorna o Registro Aleatorio
        return client;
    }

    // Passar o ID após "/clients". Caso passe um valor textual, apresentará um erro
    // Não é a Forma Padrão utilizada no Protocolo HTTP
    @GetMapping("/{id}")
    public Client getClientIdByPath(@PathVariable int id) {
        Client client = new Client();

        client.setRandomCpf();
        client.setRandomName();
        client.setId(id);

        return client;
    }

    @GetMapping
    public Client getClientIdByRequest(@RequestParam(name = "id", defaultValue = "-1") int id) {
        Client client = new Client();

        client.setRandomCpf();
        client.setRandomName();
        client.setId(id);

        return client;
    }


}
