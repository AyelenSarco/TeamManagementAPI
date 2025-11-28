
package com.crudconJPAyHibernate.jpaDemo.Service.Person;

import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Exceptions.BadRequestException;
import com.crudconJPAyHibernate.jpaDemo.Exceptions.NotFoundException;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Repository.IMemberTeamRepository;
import com.crudconJPAyHibernate.jpaDemo.Repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDate;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IMemberTeamRepository memberTeamRepository;

    @Override
    public List<Person> getPeople() {

        List<Person> people =  personRepository.findAll();

        if(people.isEmpty()) {
            throw new NotFoundException("No people found in the database");
        }

        return people;
    }

    @Override
    public Person getPerson(Long id) {

        Person person = personRepository.findById(id).orElseThrow( () -> new NotFoundException("Person not found"));

        return person;
    }

    @Override
    public Person createPerson(Person person) {

        if (person.getEmail() == null) { throw new BadRequestException("Email is required"); }
        if (personRepository.findByEmail(person.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        if (person.getFirstName() == null || person.getFirstName().isBlank()) { throw new BadRequestException("First name is required"); }
        if (person.getLastName() == null || person.getLastName().isBlank()) { throw new BadRequestException("Las name is required"); }

        person.setCreatedAt(LocalDate.now());
        person.setUpdatedAt(LocalDate.now());

        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new NotFoundException("Cannot delete a person that does not exist");
        }
        personRepository.deleteById(id);
    }

    @Override
    public Person updatePerson(Long id, PersonBaseDTO p) {
        Person dbPerson = getPerson(id);
        if (p.getAge()<0) { throw new BadRequestException("Age can not be negative"); }

        if (p.getFirstName() != null) dbPerson.setFirstName(p.getFirstName());
        else throw new BadRequestException("First name is required");

        if (p.getLastName() != null) dbPerson.setLastName(p.getLastName());
        else throw new BadRequestException("Last name is required");

        if (p.getEmail() == null) { throw new BadRequestException("Email is required"); }
        else if (personRepository.findByEmail(p.getEmail()).isPresent()) { throw new BadRequestException("Email already exists"); }
        else dbPerson.setEmail(p.getEmail());

        dbPerson.setBirthDate(p.getBirthDate());
        dbPerson.setAge(p.getAge());
        dbPerson.setUpdatedAt(LocalDate.now());

        return personRepository.save(dbPerson);
    }

    public List<MemberTeam> getTeams( Long personId){

        List<MemberTeam> membershipsList = memberTeamRepository.findByPersonId(personId).stream().toList();

        if(membershipsList.isEmpty()){
            throw new NotFoundException("No memberships found in the database for this person");
        }

        return membershipsList;
    }
}
