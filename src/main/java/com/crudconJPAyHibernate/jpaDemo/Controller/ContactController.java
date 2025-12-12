package com.crudconJPAyHibernate.jpaDemo.Controller;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactMapper;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Contact;
import com.crudconJPAyHibernate.jpaDemo.Service.Contact.IContactService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private IContactService contactService;

    @Autowired
    private ContactMapper contactMapper;

    @PostMapping("/person/{id}/contact/create")
    public ContactBaseDTO createContact(@PathVariable("id") Long id,
                                        @Valid @RequestBody ContactBaseDTO contactDTO) {

        Contact contact = contactMapper.toEntity(contactDTO, id );
        return contactMapper.toDto(contactService.createContact(contact));

    }

    @GetMapping("/person/{id}/contacts")
    public List<ContactBaseDTO> getContacts(@PathVariable Long id){
        return contactService.findAllByPersonId(id)
                .stream().map(c-> new ContactBaseDTO(c)).toList();

    }

    @DeleteMapping("/person/{personId}/contacts/{contactId}")
    public ResponseEntity<String> deleteContact(@PathVariable("personId") Long personId,
                                       @PathVariable("contactId") Long contactId){

        contactService.deleteContact(contactId);
        if ( contactService.contactExists(contactId)) {
            return ResponseEntity.ok("Contact deletion failed");
        }

        return ResponseEntity.ok("Delete contact successfully");

    }

    @PutMapping("/person/{personId}/contacts/{contactId}")
    public ContactBaseDTO updateContact(@PathVariable("personId") Long personId,
                                        @PathVariable("contactId") Long contactId,
                                        @Valid @RequestBody ContactBaseDTO contactDTO) {

        Contact contact = contactMapper.toEntity(contactDTO, personId);
        return contactMapper.toDto(contactService.updateContact(contact,contactId));
    }
}
