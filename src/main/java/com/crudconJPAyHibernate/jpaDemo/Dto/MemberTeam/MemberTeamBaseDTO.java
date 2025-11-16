package com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberTeamBaseDTO {


    @NotBlank(message = "Rol in team is missing")
    private String rolInTeam;

    @PastOrPresent(message = "Join date must be in the past or present")
    private LocalDate joinDate =  LocalDate.now();
    private boolean active;

    public MemberTeamBaseDTO() {}

    public MemberTeamBaseDTO(String rolInTeam, LocalDate joinDate,  boolean active) {

        this.rolInTeam = rolInTeam;
        this.joinDate = joinDate;
        this.active = active;
    }
}


