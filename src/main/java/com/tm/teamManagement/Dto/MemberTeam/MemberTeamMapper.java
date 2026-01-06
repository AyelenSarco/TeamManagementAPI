package com.tm.teamManagement.Dto.MemberTeam;

import com.tm.teamManagement.Model.Entity.MemberTeam;
import com.tm.teamManagement.Model.Entity.Person;
import com.tm.teamManagement.Model.Entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper( componentModel = "spring")
public interface MemberTeamMapper {

    @Mapping(target = "id", source = "memberTeam.id")
    @Mapping(target = "teamName", source="team.name")
    MembershipViewDTO toDto(MemberTeam memberTeam);



    @Mapping(target = "id", ignore = true)
    MemberTeam toEntity(MembershipBaseDTO memberTeamDTO, Person person, Team team);
}
