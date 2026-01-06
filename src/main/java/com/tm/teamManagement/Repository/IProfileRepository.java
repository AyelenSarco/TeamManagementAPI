package com.tm.teamManagement.Repository;

import com.tm.teamManagement.Model.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, Long> {

    public void deleteByPersonId(Long personId);
    public Optional<Profile> findProfileByPersonId(Long personId);
    public boolean existsByPersonId(Long personId);


}
