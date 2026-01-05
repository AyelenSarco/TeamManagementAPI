package com.crudconJPAyHibernate.jpaDemo.Controller;


import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MembershipBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Response.ApiResponse;
import com.crudconJPAyHibernate.jpaDemo.Dto.Team.TeamBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Team.TeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Team.TeamViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import com.crudconJPAyHibernate.jpaDemo.Service.Team.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> createTeam(@Valid @RequestBody TeamBaseDTO teamDTO) {
        Team team = teamMapper.toEntity(teamDTO);
        TeamViewDTO teamViewDTO = teamMapper.toDto(teamService.createTeam(team));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Team created successfully",teamViewDTO));

    }

    @GetMapping("/teams")
    public ResponseEntity<Object> getAllTeams() {

        List<TeamViewDTO> teamsDTOS = teamService.findAll().stream().map(t -> teamMapper.toDto(t)).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Teams",teamsDTOS));
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Object> getTeam(@PathVariable Long id) {

        TeamViewDTO teamDTO =  teamMapper.toDto(teamService.getTeam(id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Team",teamDTO));
    }

    @DeleteMapping("/team/{id}/delete")
    public ResponseEntity<Object> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        if(teamService.existsTeam(id)){
            return  ResponseEntity.status(HttpStatus.OK)
                            .body(ApiResponse.success("Team deletion failed",null));

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Team deleted successfully",null));

    }

    @PutMapping("/teams/{id}/update")
    public ResponseEntity<Object> updateTeam(@PathVariable Long id,
                                  @Valid @RequestBody TeamBaseDTO teamDTO) {

        Team team = teamMapper.toEntity(teamDTO);

        TeamViewDTO updatedTeamDTO = teamMapper.toDto(teamService.updateTeam(team,id));
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Team updated successfully",updatedTeamDTO));
    }


    @PostMapping("teams/{teamId}/members/add/{memberId}")
    public ResponseEntity<Object> addMemberToTeam(@PathVariable("teamId") Long teamId,
                                       @PathVariable("memberId") long memberId,
                                       @Valid @RequestBody MembershipBaseDTO memberTeamDTO) {

        teamService.addMemberToTeam(memberTeamDTO, memberId,teamId);

        TeamViewDTO teamDTO = teamMapper.toDto(teamService.getTeam(teamId));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Member added successfully to the team",teamDTO));
    }

    @PatchMapping("/teams/membership/{id}/update")
    public ResponseEntity<Object> updateMembership(@PathVariable("id") Long membershipId,
                                                   @RequestBody MembershipBaseDTO mtDTO) {

        MemberTeam membership = teamService.updateMembership(membershipId, mtDTO.rolInTeam(), mtDTO.active());


        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Membership updated successful",null));
    }


}
