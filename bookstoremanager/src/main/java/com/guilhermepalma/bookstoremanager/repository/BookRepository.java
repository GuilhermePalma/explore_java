package com.guilhermepalma.bookstoremanager.repository;

import com.guilhermepalma.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Implementa o gerenciamento com as operações do Banco de Dados, com a herença da classe
// JpaRepository<Book, Long>, em que o 1° representa a Entidade e o 2° o tipo do Identificador (ID)
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
