package com.guilhermepalma.bookstoremanager.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class BookDTO {
    @Id
    private Long id;

    @NotBlank
    @Length(max = 200)
    private String name;

    @NotNull
    private Integer pages;

    @NotNull
    private String chapter;

    @NotBlank
    @Length(max = 13)
    private String isbn;

    @NotBlank
    @Length(max = 200)
    private String publisherName;

    @Valid
    @NotNull
    private AuthorDTO author;

}
