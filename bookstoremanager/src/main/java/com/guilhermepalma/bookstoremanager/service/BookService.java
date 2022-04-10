package com.guilhermepalma.bookstoremanager.service;

import com.guilhermepalma.bookstoremanager.dto.BookDTO;
import com.guilhermepalma.bookstoremanager.dto.MessageResponseDTO;
import com.guilhermepalma.bookstoremanager.entity.Author;
import com.guilhermepalma.bookstoremanager.entity.Book;
import com.guilhermepalma.bookstoremanager.mapper.BookMapper;
import com.guilhermepalma.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsavel pelas manipulações e regras de negocios dos dados dos Livros.
 * É gerenciada pelo Spring, mas pode ser inserido outras dependencias no seu uso
 */
@Service
public class BookService {

    // Classe de Consultas no Banco de Dados de Livros
    private final BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired // Responsavel pela injeção de dependencia para usar uma Classe na classe Atual
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(BookDTO bookDTO) {

        Book book = bookMapper.toModel(bookDTO);

        // Salva e Obtem o Livro Instanciado (com o ID)
        Book savedBook = bookRepository.save(book);

        // Usando da Anotação Lambok, retorna uma Resposta com o ID do Livro Inserido
        return MessageResponseDTO.builder().build();
               // .message("ID do Livro Inserido: " + savedBook.getId())
               // .build();
    }
}
