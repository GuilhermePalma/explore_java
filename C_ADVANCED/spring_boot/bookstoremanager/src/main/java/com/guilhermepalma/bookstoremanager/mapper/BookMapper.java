package com.guilhermepalma.bookstoremanager.mapper;

import com.guilhermepalma.bookstoremanager.dto.BookModel;
import com.guilhermepalma.bookstoremanager.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface Responsavel por Mapear uma Classe {@link Book} e Converter entre BookDTO e Book
 */
@Mapper
public interface BookMapper {

    /**
     * Obtem uma Instancia da Classe {@link BookMapper} que utiliza
     * da Dependencia MapStruct para fazer a Conversão
     */
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toModel(BookModel bookModel);

    BookModel toDTO(Book book);
}
