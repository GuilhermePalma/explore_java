package com.guilhermepalma.bookstoremanager.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AuthorDTO {
    @Id
    private Long id;

    @NotBlank
    @Length(max = 200)
    private String name;

    @NotNull
    @Max(100)
    private Integer age;
}
