package com.crudconJPAyHibernate.jpaDemo.Dto.Person;

import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import lombok.Data;

import java.util.List;

@Data
public class PersonWithMembershipViewDTO extends PersonViewDTO {


    private List<MemberTeamViewDTO> membership;


    public PersonWithMembershipViewDTO(Person person) {
        super(person);

        if (person.getMembership() != null) {
            this.membership = person.getMembership().stream().map(MemberTeamViewDTO::new).toList();
        }

    }
}
