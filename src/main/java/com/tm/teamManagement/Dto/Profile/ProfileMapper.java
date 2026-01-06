package com.tm.teamManagement.Dto.Profile;

import com.tm.teamManagement.Dto.Person.PersonMapper;
import com.tm.teamManagement.Model.Entity.Person;
import com.tm.teamManagement.Model.Entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
