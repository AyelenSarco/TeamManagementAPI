package com.crudconJPAyHibernate.jpaDemo.Dto.Profile;

import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonMapper;
import com.crudconJPAyHibernate.jpaDemo.Exceptions.BadRequestException;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;
import com.crudconJPAyHibernate.jpaDemo.Repository.IPersonRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring",
    uses = PersonMapper.class
)
public interface ProfileMapper {

    @Mapping(target = "person", source = "person")
    ProfileViewDTO toDto(Profile profile);

    @Mapping(target = "person", source = "person")
    @Mapping(target ="id", ignore = true)
    Profile toEntity(ProfileBaseDTO profileBaseDTO,Person person);


}
