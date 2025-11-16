package com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam;

import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import lombok.Data;

@Data
public class MembersViewDTO extends MemberTeamBaseDTO{

    private PersonViewDTO person;

    public MembersViewDTO() {}

    public MembersViewDTO(MemberTeam memberTeam) {

        super(memberTeam.getRolInTeam(), memberTeam.getJoinDate(), memberTeam.isActive());
        this.person = new PersonViewDTO(memberTeam.getPerson());
    }
}
