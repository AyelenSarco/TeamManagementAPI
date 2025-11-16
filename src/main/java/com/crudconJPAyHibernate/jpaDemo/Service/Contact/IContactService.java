package com.crudconJPAyHibernate.jpaDemo.Service.Contact;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Contact;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface IContactService {

    public Contact createContact(Contact contact);
    public Contact getContact(Long id);
    public Contact updateContact(Contact contact, Long contactId);
    public void deleteContact(Long id);
    public Contact findContactById(Long id);
    public List<Contact> findAllByPersonId(Long id);
    public boolean contactExists(Long id);
}
