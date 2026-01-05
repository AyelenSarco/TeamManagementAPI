
package com.crudconJPAyHibernate.jpaDemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long>{

    Optional<Person> findByEmail(String email);


}
