package com.crudconJPAyHibernate.jpaDemo.Dto.Person;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


@Data
public class PersonViewDTO extends PersonBaseDTO{

    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<ContactBaseDTO> contacts;

    public PersonViewDTO() {}

    public PersonViewDTO(Person person) {
        super(person);
        this.id = person.getId();
        this.createdAt = person.getCreatedAt();
        this.updatedAt = person.getUpdatedAt();
        if (person.getContacts() != null) {
            this.contacts = person.getContacts().stream().map(ContactBaseDTO::new).toList();
        }


    }
}
