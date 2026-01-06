package com.tm.teamManagement.Repository;

import com.tm.teamManagement.Model.Entity.MemberTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMemberTeamRepository extends JpaRepository<MemberTeam, Long> {

    boolean existsByPersonIdAndTeamId(Long personId, Long teamId);
    List<MemberTeam> findByPersonId(Long personId);
}
