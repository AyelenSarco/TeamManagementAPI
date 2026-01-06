package com.tm.teamManagement.Dto.Person;

import com.tm.teamManagement.Dto.Contact.ContactBaseDTO;
import com.tm.teamManagement.Dto.MemberTeam.MembershipViewDTO;
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
