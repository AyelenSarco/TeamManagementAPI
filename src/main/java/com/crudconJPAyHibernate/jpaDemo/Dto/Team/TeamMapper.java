package com.crudconJPAyHibernate.jpaDemo.Dto.Team;

import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    @Autowired
    private MemberTeamMapper memberTeamMapper;

    public Team toEntity(TeamBaseDTO dto) {
        Team team = new Team();

        team.setName(dto.getName());
        team.setDescription(dto.getDescription());


        return team;
    }


    public TeamViewDTO toDTO(Team team) {
        return new TeamViewDTO(team);
    }


}
