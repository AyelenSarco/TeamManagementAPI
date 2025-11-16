package com.crudconJPAyHibernate.jpaDemo.Dto.Profile;

import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonMapper;
import com.crudconJPAyHibernate.jpaDemo.Exceptions.BadRequestException;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;
import com.crudconJPAyHibernate.jpaDemo.Repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    public Profile toEntity(ProfileBaseDTO dto, Long idPerson) {
        Profile profile = new Profile();

        Person person = personRepository.findById(idPerson)
                .orElseThrow(() -> new BadRequestException("Person not found"));



        profile.setPerson(person);
        profile.setBiography(dto.getBiography());
        profile.setAvatarUrl(dto.getAvatarUrl());
        profile.setNationality(dto.getNationality());
        profile.setGender(dto.getGender());
        profile.setLinkedinUrl(dto.getLinkedinUrl());

        return profile;
    }

    public ProfileViewDTO toDto(Profile profile) {
        return new ProfileViewDTO(profile);

    }
}
