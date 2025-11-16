package com.crudconJPAyHibernate.jpaDemo.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@Table(name="teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "team")
    List<MemberTeam> memberships;

    public void addMembership(MemberTeam memberTeam ){
        memberships.add(memberTeam);
    }
}
