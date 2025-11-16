package com.crudconJPAyHibernate.jpaDemo.Dto.Profile;

import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;
import com.crudconJPAyHibernate.jpaDemo.Model.Enum.Gender;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileBaseDTO {



    @Size(min = 1, max = 250, message = "The biography should have between 1 to 250 characters")
    private String biography;

    private String avatarUrl;
    private String nationality;

    private Gender gender;
    private String linkedinUrl;

    public ProfileBaseDTO() {}

    public ProfileBaseDTO(String biography,
                          String avatarUrl, String nationality,
                          Gender gender, String linkedinUrl) {
        this.biography = biography;
        this.avatarUrl = avatarUrl;
        this.nationality = nationality;
        this.gender = gender;
        this.linkedinUrl = linkedinUrl;
    }

    public ProfileBaseDTO(Profile profile) {
        this.biography = profile.getBiography();
        this.avatarUrl = profile.getAvatarUrl();
        this.nationality = profile.getNationality();
        this.gender = profile.getGender();
        this.linkedinUrl = profile.getLinkedinUrl();
    }
}
