package com.guilhermepalma.bookstoremanager.controller;

import com.guilhermepalma.bookstoremanager.dto.MessageResponseDTO;
import com.guilhermepalma.bookstoremanager.entity.Book;
import com.guilhermepalma.bookstoremanager.repository.BookRepository;
import com.guilhermepalma.bookstoremanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Essa Classe Representa um Controlador de uma API REST, com o endpoint "/api/b1/books"
 * que recebe os dados que serão utilizados.
 */
@RestController // Anotação para representar uma classe de Controlador REST API
@RequestMapping("/api/v1/books") // Define a URL de Acesso (nomesite.com/api/v1/books)
public class BookController {

    private final BookService bookService;

    @Autowired // Responsavel pela injeção de dependencia para usar uma Classe na classe Atual
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping // Define que será uma solicitação POST
    public MessageResponseDTO create(@RequestBody Book book) {
        // A Classe Controller Recebe os Dados e a manipulação fica com a Classe Service
        return bookService.create(book);
    }

}
