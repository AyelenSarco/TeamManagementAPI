package com.tm.teamManagement.Repository;

import com.tm.teamManagement.Model.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITeamRepository extends JpaRepository<Team, Long> {

     boolean existsByName(String name);

    @Query("""
    SELECT t FROM Team t
    LEFT JOIN FETCH t.memberships m
    LEFT JOIN FETCH m.person
    WHERE t.id = :id
    """)
    Optional<Team> findByIdWithMembersAndPerson(Long id);
}

