package com.crudconJPAyHibernate.jpaDemo.Dto.Person;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MembershipViewDTO;
import jakarta.validation.Valid;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public class PersonWithMembershipViewDTO{

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private LocalDate birthDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @Valid
    private List<ContactBaseDTO> contacts;
    @Valid
    private List<MembershipViewDTO> membership;



}
