package com.crudconJPAyHibernate.jpaDemo.Dto.Person;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MembershipViewDTO;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;


public record PersonViewDTO (

    Long id,
    String firstName,
    String lastName,
    int age,
    String email,
    LocalDate birthDate,
    LocalDate createdAt,
    LocalDate updatedAt,
    @Valid
    List<ContactBaseDTO> contacts,
    @Valid
    List<MembershipViewDTO> membership

){
}
