package com.guilhermepalma.bookstoremanager.controller;

import com.guilhermepalma.bookstoremanager.dto.MessageResponseDTO;
import com.guilhermepalma.bookstoremanager.entity.Book;
import com.guilhermepalma.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Essa Classe Representa um Controlador de uma API REST, com o endpoint "/api/b1/books")
 */
@RestController // Anotação para representar uma classe de Controlador REST API
@RequestMapping("/api/v1/books") // Define a URL de Acesso (nomesite.com/api/v1/books)
public class BookController {

    // Classe de Consultas no Banco de Dados de Livros
    private final BookRepository bookRepository;

    @Autowired // Responsavel pela injeção de dependencia para usar uma Classe na classe Atual
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping // Define que será uma solicitação POST
    public MessageResponseDTO create(@RequestBody Book book) {
        // Salva e Obtem o Livro Instanciado (com o ID)
        Book savedBook = bookRepository.save(book);

        // Usando da Anotação Lambok, retorna uma Resposta com o ID do Livro Inserido
        return MessageResponseDTO.builder()
                .message("ID do Livro Inserido" + savedBook.getId())
                .build();
    }

}
