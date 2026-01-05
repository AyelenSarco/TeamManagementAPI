package com.crudconJPAyHibernate.jpaDemo.Dto.Person;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;



@Mapper ( componentModel = "spring",
            uses = {ContactMapper.class, MemberTeamMapper.class})
public interface PersonMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target= "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "membership", source = "membership")
    PersonViewDTO toDto(Person person);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "birthDate", source = "birthDate")
    Person toEntity(PersonBaseDTO personDto);
}
