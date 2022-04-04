package com.guilhermepalma.bookstoremanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class AuthorModel {
    @Id
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    @Size(max = 110)
    private Integer age;
}
