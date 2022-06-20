package com.guilhermepalma.bookstoremanager.dto;

import com.guilhermepalma.bookstoremanager.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe Responsavel por Servir de Modelo e Fazer a Validação dos Itens
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {

    @Id
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    private Integer pages;

    @NotNull
    private String chapter;

    @NotBlank
    private String isbn;

    @NotBlank
    @Size(max = 200)
    private String publisherName;

    @Valid
    @NotNull
    @OneToOne
    private AuthorModel author;
}
