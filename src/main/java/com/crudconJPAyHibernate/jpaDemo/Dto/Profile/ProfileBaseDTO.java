package com.crudconJPAyHibernate.jpaDemo.Dto.Profile;

import com.crudconJPAyHibernate.jpaDemo.Model.Enum.Gender;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ProfileBaseDTO (

    @Size(min = 1, max = 250, message = "The biography should have between 1 to 250 characters")
    String biography,
    String avatarUrl,
    String nationality,
    Gender gender,
    String linkedinUrl

){
}
