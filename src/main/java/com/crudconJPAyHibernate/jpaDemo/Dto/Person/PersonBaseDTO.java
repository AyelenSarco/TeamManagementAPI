package com.crudconJPAyHibernate.jpaDemo.Dto.Person;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonBaseDTO {

    @Size(min = 2, max = 50)
    @NotBlank (message = "First Name is missing")
    private String firstName;

    @Size(min = 2, max = 50)
    @NotBlank(message = "Last Name is missing")
    private String lastName;

    private int age;

    @NotBlank (message = "Email is missing")
    @Email (message = "Invalid email address")
    private String email;

    @Past (message = "Birthday date must be in the past.")
    private LocalDate birthDate;


    public PersonBaseDTO() {}

    public PersonBaseDTO(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.age = person.getAge();
        this.email = person.getEmail();
        this.birthDate = person.getBirthDate();
    }
}
