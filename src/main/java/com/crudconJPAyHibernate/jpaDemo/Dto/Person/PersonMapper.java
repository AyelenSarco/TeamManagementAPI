package com.crudconJPAyHibernate.jpaDemo.Dto.Person;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private MemberTeamMapper memberTeamMapper;

    public Person toEntity(PersonBaseDTO dto) {
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAge(dto.getAge());
        person.setEmail(dto.getEmail());
        person.setBirthDate(dto.getBirthDate());

        return person;
    }

    public PersonViewDTO toPersonViewDTO(Person person) {

        return new PersonViewDTO(person);
    }

    public PersonWithMembershipViewDTO toPersonWithMembershipViewDTO(Person person) {
        return new PersonWithMembershipViewDTO(person);
    }
}
