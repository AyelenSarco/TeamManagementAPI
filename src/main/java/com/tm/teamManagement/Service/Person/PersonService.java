
package com.tm.teamManagement.Service.Person;

import com.tm.teamManagement.Dto.Person.PersonBaseDTO;
import com.tm.teamManagement.Exceptions.BadRequestException;
import com.tm.teamManagement.Exceptions.NotFoundException;
import com.tm.teamManagement.Model.Entity.MemberTeam;
import com.tm.teamManagement.Model.Entity.Person;
import com.tm.teamManagement.Repository.IMemberTeamRepository;
import com.tm.teamManagement.Repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService implements IPersonService {


    private final IPersonRepository personRepository;
    private final IMemberTeamRepository memberTeamRepository;

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
        if (p.age()<0) { throw new BadRequestException("Age can not be negative"); }

        if (p.firstName() != null) dbPerson.setFirstName(p.firstName());
        else throw new BadRequestException("First name is required");

        if (p.lastName() != null) dbPerson.setLastName(p.lastName());
        else throw new BadRequestException("Last name is required");

        if (p.email() == null) { throw new BadRequestException("Email is required"); }
        else if (personRepository.findByEmail(p.email()).isPresent()) { throw new BadRequestException("Email already exists"); }
        else dbPerson.setEmail(p.email());

        dbPerson.setBirthDate(p.birthDate());
        dbPerson.setAge(p.age());
        dbPerson.setUpdatedAt(LocalDate.now());

        return personRepository.save(dbPerson);
    }

    public List<MemberTeam> getTeams( Long personId){

        List<MemberTeam> membershipsList = memberTeamRepository.findByPersonId(personId)
                .stream()
                .toList();

        if(membershipsList.isEmpty()){
            throw new NotFoundException("No memberships found in the database for this person");
        }

        return membershipsList;
    }

    public boolean existsById(Long id){
        return personRepository.existsById(id);
    }


}
