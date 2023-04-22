package com.guilhermepalma.bookstoremanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // Permite a Notação dass propriedades da Classe
@Data // Gera os Getter e Setters
@Builder // Gera o Construtor
@NoArgsConstructor // Cria Construtores da Classe com os Parametros
@AllArgsConstructor // Cria Construtores da Classe sem os Parametros
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer age;
}
