package com.crudconJPAyHibernate.jpaDemo.Repository;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMemberTeamRepository extends JpaRepository<MemberTeam, Long> {

    boolean existsByPersonIdAndTeamId(Long personId, Long teamId);
    List<MemberTeam> findByPersonId(Long personId);
}
