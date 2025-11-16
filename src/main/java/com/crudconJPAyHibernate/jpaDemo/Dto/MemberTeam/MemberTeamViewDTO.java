package com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import lombok.Data;

@Data
public class MemberTeamViewDTO extends MemberTeamBaseDTO {

    private String teamName;


    public MemberTeamViewDTO() {}

    public MemberTeamViewDTO(MemberTeam memberTeam) {
        super(memberTeam.getRolInTeam(), memberTeam.getJoinDate(),memberTeam.isActive());


        this.teamName = memberTeam.getTeam().getName();
    }
}
