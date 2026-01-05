package com.crudconJPAyHibernate.jpaDemo.Dto.Team;

import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MembershipViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

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
