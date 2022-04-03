package com.guilhermepalma.bookstoremanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private String chapter;

    @Column(nullable = false)
    private String isbn;

    @Column(name = "publisher_name", nullable = false, unique = true)
    private String publisherName;

    // ManyToOne = Representa o Relacionamento entre as Tabelas.
    //   - FetchType.LAZY = Estrategia para obter os Livros e Autores de forma automizado
    //   - cascade = Sincroniza as Operações do Livro com o Autor (Inserção, Exclusão, etc)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "author_id")
    private Author author;

}
