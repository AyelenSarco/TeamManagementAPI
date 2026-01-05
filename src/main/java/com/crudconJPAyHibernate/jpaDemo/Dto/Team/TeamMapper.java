package com.crudconJPAyHibernate.jpaDemo.Dto.Team;

import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MembershipViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper (componentModel = "spring",
        uses = MemberTeamMapper.class
)
public interface TeamMapper {

    @Mapping(target = "memberships", source = "memberships")
    TeamViewDTO toDto(Team team);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "memberships", ignore = true)
    Team toEntity(TeamBaseDTO teamDTO);

}
