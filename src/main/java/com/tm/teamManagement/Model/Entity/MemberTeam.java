package com.tm.teamManagement.Model.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name="members_team")
public class MemberTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nullable=false + @ForeignKey → forces the relationship to exist in the database.
    @ManyToOne
    @JoinColumn(name="id_person", nullable = false, foreignKey = @ForeignKey(name = "fk_member_person"))
    private Person person;

    @ManyToOne
    @JoinColumn(name="id_team", nullable = false, foreignKey = @ForeignKey(name = "fk_member_team"))
    private Team team;

    private String rolInTeam;
    private LocalDate joinDate = LocalDate.now();

    private boolean active = true;

}
