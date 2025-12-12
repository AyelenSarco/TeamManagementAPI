package com.crudconJPAyHibernate.jpaDemo.Controller;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonWithMembershipViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Service.Contact.IContactService;
import com.crudconJPAyHibernate.jpaDemo.Service.Person.IPersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PersonController {


    private final IPersonService personService;

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private ContactMapper contactMapper;

    @GetMapping("/people")
    public List<PersonWithMembershipViewDTO> getPeople(){

        return personService.getPeople().stream()
                .map(PersonWithMembershipViewDTO::new).toList();

    }

    @GetMapping("/person/{id}")
    public PersonWithMembershipViewDTO getPerson(@PathVariable Long id){
        return personMapper.toPersonWithMembershipViewDTO(personService.getPerson(id));
    }

    @PostMapping("/person/create")
    public PersonViewDTO createPerson(@Valid @RequestBody PersonBaseDTO personBaseDTO){

        Person person = personService.createPerson(personMapper.toEntity(personBaseDTO));
        return personMapper.toPersonWithMembershipViewDTO(person);
    }

    @DeleteMapping("/person/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        if (personService.existsById(id) ){
            return ResponseEntity.ok("Person deletion failed");
        }
        return ResponseEntity.ok("Delete person successfully");
    }

    @PutMapping("/person/update/{id}")
    public PersonViewDTO updatedPerson(@Valid @RequestBody PersonBaseDTO personBaseDTO,
                                       @PathVariable Long id){


        Person person = personService.updatePerson(id,personBaseDTO);
        return personMapper.toPersonWithMembershipViewDTO(person);

    }

    @GetMapping("/person/{id}/teams")
    public List<MemberTeamViewDTO>  getTeams(@PathVariable("id") Long personId){

        List<MemberTeam> membershipList = personService.getTeams(personId);

        return membershipList.stream().map(MemberTeamViewDTO::new).toList();

    }




}
