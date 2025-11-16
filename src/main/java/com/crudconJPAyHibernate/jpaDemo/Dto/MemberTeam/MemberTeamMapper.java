package com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam;

import com.crudconJPAyHibernate.jpaDemo.Exceptions.NotFoundException;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import com.crudconJPAyHibernate.jpaDemo.Repository.ITeamRepository;
import com.crudconJPAyHibernate.jpaDemo.Repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberTeamMapper {

    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private ITeamRepository teamRepository;

    public MemberTeam toEntity(MemberTeamBaseDTO dto, Long memberId, Long teamId) {
        MemberTeam memberTeam = new MemberTeam();

        Person person = personRepository.findById(memberId)
                                .orElseThrow( () -> new NotFoundException("Person not found"));

        Team team = teamRepository.findById(teamId)
                                .orElseThrow( () -> new NotFoundException("Team not found"));

        memberTeam.setPerson(person);
        memberTeam.setTeam(team);
        memberTeam.setRolInTeam(dto.getRolInTeam());
        memberTeam.setJoinDate(dto.getJoinDate());
        return memberTeam;

    }

    public MemberTeamViewDTO ToDTO(MemberTeam memberTeam) {
        return new MemberTeamViewDTO(memberTeam);

    }
}
