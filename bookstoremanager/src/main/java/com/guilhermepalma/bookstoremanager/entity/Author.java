package com.guilhermepalma.bookstoremanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// Permite a Notação dass propriedades da Classe
@Entity
// Gera os Getter e Setters
@Data
// Gera o Construtor
@Builder
// Cria Construtores da Classe com e sem os Parametros
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer age;
}
