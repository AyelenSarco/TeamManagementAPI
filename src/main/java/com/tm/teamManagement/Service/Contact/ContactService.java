package com.tm.teamManagement.Service.Contact;


import com.tm.teamManagement.Exceptions.ConflictException;
import com.tm.teamManagement.Exceptions.NotFoundException;
import com.tm.teamManagement.Model.Entity.Contact;
import com.tm.teamManagement.Repository.IContactRepository;
import com.tm.teamManagement.Repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactService implements IContactService {


    private final IContactRepository contactRepository;
    private final IPersonRepository personRepository;

    @Override
    public List<Contact> findAllByPersonId(Long person_id) {
        return contactRepository.findAllByPersonId(person_id);

    }

    @Override
    public Contact createContact(Contact contact) {
        if (! personRepository.existsById(contact.getPerson().getId())) {
            throw new NotFoundException("Person not found");
        }
        if (contact.isMajor() && primaryContactExists(contact.getPerson().getId())) {
            throw new ConflictException("A primary contact already exists");
        }

        return contactRepository.save(contact);
    }


    @Override
    public Contact getContact(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Contact not found"));
    }

    @Override
    public boolean contactExists(Long id) {
        return contactRepository.existsById(id);
    }

    @Override
    public Contact updateContact(Contact contact, Long contactId) {
        //Contact dbContact = this.getContact(contactId);

        Contact dbContact = contactRepository.findAllByPersonId(contact.getPerson().getId())
                .stream()
                .filter(c -> c.getId().equals(contactId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Contact not found"));

        if (contact.isMajor() && primaryContactExists(contact.getPerson().getId())) {
            throw new ConflictException("A primary contact already exists");
        }


        dbContact.setMajor(contact.isMajor());
        if (contact.getContactType() != null) {
            dbContact.setContactType(contact.getContactType());
        }
        if (contact.getNotes() != null) {
            dbContact.setNotes(contact.getNotes());
        }
        if (contact.getValue() != null) {
            dbContact.setValue(contact.getValue());
        }
        return contactRepository.save(dbContact);

    }

    @Override
    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new NotFoundException("Contact not found");
        }
        contactRepository.deleteById(id);
    }

    @Override
    public Contact findContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Contact not found"));
    }

    private boolean primaryContactExists(Long personId) {
        if (findAllByPersonId(personId) != null) {
            for (Contact c : findAllByPersonId(personId)) {
                if (c.isMajor()) {
                    return true;
                }
            }
        }

        return false;
    }






}
