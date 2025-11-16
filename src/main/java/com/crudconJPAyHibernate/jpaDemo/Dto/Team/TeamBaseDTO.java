package com.crudconJPAyHibernate.jpaDemo.Dto.Team;

import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Team;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TeamBaseDTO {


    @NotBlank(message = "Team name is missing")
    private String name;
    private String description;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    public TeamBaseDTO() {}

    public TeamBaseDTO(String name, String description, LocalDate createdAt, LocalDate updatedAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;


    }

}
