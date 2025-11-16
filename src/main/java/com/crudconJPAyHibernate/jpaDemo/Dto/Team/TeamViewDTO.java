package com.crudconJPAyHibernate.jpaDemo.Dto.Team;

import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MembersViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import lombok.Data;

import java.util.List;

@Data
public class TeamViewDTO extends TeamBaseDTO {
    private List<MembersViewDTO> members;


    public TeamViewDTO(Team team) {
        super(team.getName(),team.getDescription(),team.getCreatedAt(),team.getUpdatedAt());

        if(team.getMemberships() != null) {
            this.members = team.getMemberships().stream().map(MembersViewDTO::new).toList();
        }
    }
}
