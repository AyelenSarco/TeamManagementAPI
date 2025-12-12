package com.crudconJPAyHibernate.jpaDemo.Controller;


import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Team.TeamBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Team.TeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Team.TeamViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import com.crudconJPAyHibernate.jpaDemo.Service.Team.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private MemberTeamMapper memberTeamMapper;


    @PostMapping("/team/create")
    public TeamViewDTO createTeam(@Valid @RequestBody TeamBaseDTO teamDTO) {
        Team team = teamMapper.toEntity(teamDTO);
        return teamMapper.toDTO(teamService.createTeam(team));

    }

    @GetMapping("/teams")
    public List<TeamViewDTO> getAllTeams() {
        return teamService.findAll().stream().map(TeamViewDTO::new).toList();
    }

    @GetMapping("/team/{id}")
    public TeamViewDTO getTeam(@PathVariable Long id) {

        return  teamMapper.toDTO(teamService.getTeam(id));
    }

    @DeleteMapping("/team/{id}/delete")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        if(teamService.existsTeam(id)){
            return  ResponseEntity.ok("Team deletion failed");
        }
        return ResponseEntity.ok("Team deletion successful");
    }

    @PutMapping("/teams/{id}/update")
    public TeamViewDTO updateTeam(@PathVariable Long id,
                                  @Valid @RequestBody TeamBaseDTO teamDTO) {

        Team team = teamMapper.toEntity(teamDTO);

        return teamMapper.toDTO(team);
    }


    @PostMapping("teams/{teamId}/members/add/{memberId}")
    public TeamViewDTO addMemberToTeam(@PathVariable("teamId") Long teamId,
                                       @PathVariable("memberId") long memberId,
                                       @Valid @RequestBody MemberTeamBaseDTO memberTeamDTO) {
        MemberTeam mt = memberTeamMapper.toEntity(memberTeamDTO,memberId, teamId);
        teamService.addMemberToTeam(mt);

        return teamMapper.toDTO(teamService.getTeam(teamId));
    }

    @PatchMapping("/teams/membership/{id}/update")
    public ResponseEntity<String> updateMembership(@PathVariable("id") Long membershipId,
                                                   @RequestBody MemberTeamBaseDTO mtDTO) {

        MemberTeam membership = teamService.updateMembership(membershipId, mtDTO.getRolInTeam(), mtDTO.isActive());
        return ResponseEntity.ok("Membership updated successful");

    }


}
