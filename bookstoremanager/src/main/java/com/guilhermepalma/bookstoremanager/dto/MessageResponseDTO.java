package com.guilhermepalma.bookstoremanager.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Classe que representa uma Mensagem para uma Solicitação
 * DTO = Data Transfer Objetct (Objeto de Transferencia de Dados)
 */
// Data: Getters e Setters | Builder: Builder e Build da Classe (Notações do Lombok)
@Data
@Builder
public class MessageResponseDTO {
    private String message;
}
