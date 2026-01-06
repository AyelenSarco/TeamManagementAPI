package com.tm.teamManagement.Dto.Team;

import com.tm.teamManagement.Dto.MemberTeam.MemberTeamMapper;
import com.tm.teamManagement.Model.Entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
