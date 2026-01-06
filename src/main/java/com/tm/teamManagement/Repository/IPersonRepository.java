
package com.tm.teamManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tm.teamManagement.Model.Entity.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long>{

    Optional<Person> findByEmail(String email);


}
