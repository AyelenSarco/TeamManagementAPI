package com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam;

import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Team.TeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
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
