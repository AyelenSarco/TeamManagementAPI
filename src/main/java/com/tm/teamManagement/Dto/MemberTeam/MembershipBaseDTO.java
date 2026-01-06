package com.tm.teamManagement.Dto.MemberTeam;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDate;


@Builder
public record MembershipBaseDTO (


    @NotBlank(message = "Rol in team is missing")
    String rolInTeam,

    @PastOrPresent(message = "Join date must be in the past or present")
    LocalDate joinDate,
    boolean active

    ){

        public MembershipBaseDTO{
            if (joinDate == null){
                joinDate = LocalDate.now();
            }
        }
}


