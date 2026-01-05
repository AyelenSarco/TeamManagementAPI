package com.crudconJPAyHibernate.jpaDemo.Service.Team;

import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MembershipBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;

import java.lang.reflect.Member;
import java.util.List;

public interface ITeamService {

    public Team createTeam(Team team);
    public Team updateTeam(Team team, Long id);
    public void deleteTeam(Long teamId);
    public Team getTeam(Long id);
    public List<Team> findAll();
    public MemberTeam addMemberToTeam(MembershipBaseDTO memberTeamDTO, Long teamId, Long memberId);
    public boolean existsTeam(Long teamId);
    public MemberTeam updateMembership(Long id, String rolInTeam, boolean active);

}
