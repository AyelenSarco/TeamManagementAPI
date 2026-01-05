package com.crudconJPAyHibernate.jpaDemo.Dto.Profile;


import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;
import com.crudconJPAyHibernate.jpaDemo.Model.Enum.Gender;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ProfileViewDTO (

    @Size(min = 1, max = 250, message = "The biography should have between 1 to 250 characters")
    String biography,
    String avatarUrl,
    String nationality,
    Gender gender,
    String linkedinUrl,
    PersonViewDTO person
){
}
