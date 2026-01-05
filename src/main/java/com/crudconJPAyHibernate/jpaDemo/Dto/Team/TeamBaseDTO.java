package com.crudconJPAyHibernate.jpaDemo.Dto.Team;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TeamBaseDTO (


    @NotBlank(message = "Team name is missing")
    String name,
    String description,
    LocalDate createdAt,
    LocalDate updatedAt

){
}
