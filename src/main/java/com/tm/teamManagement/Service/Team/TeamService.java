package com.tm.teamManagement.Service.Team;

import com.tm.teamManagement.Dto.MemberTeam.MemberTeamMapper;
import com.tm.teamManagement.Dto.MemberTeam.MembershipBaseDTO;
import com.tm.teamManagement.Exceptions.BadRequestException;
import com.tm.teamManagement.Exceptions.NotFoundException;
import com.tm.teamManagement.Model.Entity.MemberTeam;
import com.tm.teamManagement.Model.Entity.Person;
import com.tm.teamManagement.Model.Entity.Team;
import com.tm.teamManagement.Repository.IMemberTeamRepository;
import com.tm.teamManagement.Repository.IPersonRepository;
import com.tm.teamManagement.Repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService implements ITeamService {

    private final ITeamRepository teamRepository;
    private final IPersonRepository personRepository;
    private final IMemberTeamRepository memberTeamRepository;
    private final MemberTeamMapper memberTeamMapper;

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
    public MemberTeam addMemberToTeam(MembershipBaseDTO memberTeamDTO, Long memberId, Long teamId) {

        Person person = personRepository.findById(memberId)
                .orElseThrow( () -> new NotFoundException("Person not found"));

        Team team = teamRepository.findById(teamId)
                .orElseThrow( () -> new NotFoundException("Team not found"));

        if (memberTeamRepository.existsByPersonIdAndTeamId(memberId, teamId)) {
            throw new BadRequestException("The person is already a member of this team");
        }

        MemberTeam memberTeam = memberTeamMapper.toEntity(memberTeamDTO,person, team);
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
