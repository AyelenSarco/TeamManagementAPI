package com.crudconJPAyHibernate.jpaDemo.Controller;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Response.ApiResponse;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Contact;
import com.crudconJPAyHibernate.jpaDemo.Service.Contact.IContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContactController {


    private final IContactService contactService;

    @Autowired
    private ContactMapper contactMapper;

    @PostMapping("/person/{id}/contact/create")
    public ResponseEntity<Object> createContact(@PathVariable("id") Long id,
                                        @Valid @RequestBody ContactBaseDTO contactDTO) {

        Contact contact = contactMapper.toEntity(contactDTO, id );
        ContactBaseDTO newContactDTO =  contactMapper.toDto(contactService.createContact(contact));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Contact",contactDTO));
    }

    @GetMapping("/person/{id}/contacts")
    public ResponseEntity<Object> getContacts(@PathVariable Long id){
        List<ContactBaseDTO> contactsDTOs =  contactService.findAllByPersonId(id)
                .stream().map(ContactBaseDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Contacts for the person with ID " + id + ".",contactsDTOs));

    }

    @DeleteMapping("/person/{personId}/contacts/{contactId}")
    public ResponseEntity<Object> deleteContact(@PathVariable("personId") Long personId,
                                       @PathVariable("contactId") Long contactId){

        contactService.deleteContact(contactId);
        if ( contactService.contactExists(contactId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.failure("Contact deletion failed",null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Delete contact successfully",null));
    }

    @PutMapping("/person/{personId}/contacts/{contactId}")
    public ResponseEntity<Object> updateContact(@PathVariable("personId") Long personId,
                                        @PathVariable("contactId") Long contactId,
                                        @Valid @RequestBody ContactBaseDTO contactDTO) {

        Contact contact = contactMapper.toEntity(contactDTO, personId);
        ContactBaseDTO updatedContactDTO =  contactMapper.toDto(contactService.updateContact(contact,contactId));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Contact updated successfully",updatedContactDTO));
    }
}
