package com.crudconJPAyHibernate.jpaDemo.Dto.Contact;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Contact;
import com.crudconJPAyHibernate.jpaDemo.Model.Enum.ContactType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactBaseDTO {


    @NotNull(message = "Contact type is missing")
    private ContactType contactType;
    private String value;
    private boolean major;
    private String notes;


    public ContactBaseDTO() {}

    public ContactBaseDTO(Contact contact) {
        this.contactType = contact.getContactType();
        this.value = contact.getValue();
        this.major = contact.isMajor();
        this.notes = contact.getNotes();
    }

}
