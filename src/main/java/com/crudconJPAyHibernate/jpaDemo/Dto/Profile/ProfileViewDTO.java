package com.crudconJPAyHibernate.jpaDemo.Dto.Profile;


import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;
import lombok.Data;

@Data
public class ProfileViewDTO extends ProfileBaseDTO {

    private PersonViewDTO personViewDto;

    public ProfileViewDTO(Profile profile) {

        super(profile.getBiography(),profile.getAvatarUrl(),
                profile.getNationality(),profile.getGender(),
                profile.getLinkedinUrl());

        this.personViewDto = new PersonViewDTO(profile.getPerson());
    }
}
