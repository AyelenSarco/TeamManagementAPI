package com.tm.teamManagement.Repository;

import com.tm.teamManagement.Model.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByPersonId(Long id);
}
