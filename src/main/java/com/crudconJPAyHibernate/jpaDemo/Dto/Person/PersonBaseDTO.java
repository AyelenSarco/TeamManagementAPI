package com.crudconJPAyHibernate.jpaDemo.Dto.Person;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
public record PersonBaseDTO (

    @Size(min = 2, max = 50)
    @NotBlank (message = "First Name is missing")
    String firstName,

    @Size(min = 2, max = 50)
    @NotBlank(message = "Last Name is missing")
    String lastName,

    int age,

    @NotBlank (message = "Email is missing")
    @Email (message = "Invalid email address")
    String email,

    @Past (message = "Birthday date must be in the past.")
    LocalDate birthDate
    ){

}
