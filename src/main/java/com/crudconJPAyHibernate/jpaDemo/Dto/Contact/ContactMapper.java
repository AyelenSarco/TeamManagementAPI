package com.crudconJPAyHibernate.jpaDemo.Dto.Contact;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Contact;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Repository.IPersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContactMapper {

    @Autowired
    private IPersonRepository personRepository;

    public Contact toEntity(ContactBaseDTO dto, Long idPerson){
        Contact contact = new Contact();

        Person person = personRepository.findById(idPerson)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        contact.setPerson(person);
        contact.setContactType(dto.getContactType());
        contact.setValue(dto.getValue());
        contact.setMajor(dto.isMajor());
        contact.setNotes(dto.getNotes());

        return contact;
    }

    public ContactBaseDTO toDto(Contact contact){

        return new ContactBaseDTO(contact);
    }
}
