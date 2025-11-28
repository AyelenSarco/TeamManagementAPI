package com.crudconJPAyHibernate.jpaDemo.Service.Team;

import com.crudconJPAyHibernate.jpaDemo.Exceptions.BadRequestException;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import com.crudconJPAyHibernate.jpaDemo.Repository.IMemberTeamRepository;
import com.crudconJPAyHibernate.jpaDemo.Repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.List;

@Service
public class TeamService implements ITeamService {
    @Autowired
    private ITeamRepository teamRepository;
    @Autowired
    private IMemberTeamRepository memberTeamRepository;
    @Override
    public Team createTeam(Team team) {
        if (team.getName() == null) { throw new BadRequestException("Team name is required");}
        if (teamRepository.existsByName(team.getName())){
            throw new BadRequestException("Already exist a team with name "+ team.getName());
        }
        team.setCreatedAt(LocalDate.now());
        team.setUpdatedAt(LocalDate.now());
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team newData, Long id) {
        Team team_db = getTeam(id);
        team_db.setName(newData.getName());
        team_db.setDescription(newData.getDescription());
        team_db.setUpdatedAt(LocalDate.now());

        return teamRepository.save(team_db);
    }

    @Override
    public void deleteTeam(Long teamId) {
        if (! teamRepository.existsById(teamId)) {
            throw new BadRequestException("Team does not exist");
        }
        teamRepository.deleteById(teamId);
    }

    @Override
    public Team getTeam(Long id) {
        return teamRepository.findByIdWithMembersAndPerson(id).orElseThrow(() -> new BadRequestException("Team not found"));
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public MemberTeam addMemberToTeam(MemberTeam memberTeam) {

        Long personId = memberTeam.getPerson().getId();
        Long teamId = memberTeam.getTeam().getId();
        if (memberTeamRepository.existsByPersonIdAndTeamId(personId, teamId)) {
            throw new BadRequestException("The person is already a member of this team");
        }

        return memberTeamRepository.save(memberTeam);
    }

    @Override
    public boolean existsTeam(Long teamId) {
        return teamRepository.existsById(teamId);
    }

    @Override
    public MemberTeam updateMembership(Long id, String rolInTeam, boolean active) {

        MemberTeam dbMembership = memberTeamRepository.findById(id).orElseThrow(() -> new BadRequestException("Membership does not exist"));
        dbMembership.setActive(active);
        dbMembership.setRolInTeam(rolInTeam);
        return memberTeamRepository.save(dbMembership);
    }
}
