package com.tm.teamManagement.Dto.Team;

import com.tm.teamManagement.Dto.MemberTeam.MembershipViewDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record TeamViewDTO  (


    @NotBlank(message = "Team name is missing")
    String name,
    String description,
    LocalDate createdAt,
    LocalDate updatedAt,

    @Valid
    List<MembershipViewDTO> memberships


){
}
