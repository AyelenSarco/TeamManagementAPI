package com.tm.teamManagement.Dto.MemberTeam;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MembershipViewDTO (
    Long id,
    String rolInTeam,
    LocalDate joinDate,
    boolean active,
    String teamName


){
}
