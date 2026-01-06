package com.tm.teamManagement.Dto.Profile;


import com.tm.teamManagement.Dto.Person.PersonViewDTO;
import com.tm.teamManagement.Model.Enum.Gender;
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
