package com.crudconJPAyHibernate.jpaDemo.Controller;

import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Contact.ContactMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam.MemberTeamViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonWithMembershipViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Response.ApiResponse;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Service.Contact.IContactService;
import com.crudconJPAyHibernate.jpaDemo.Service.Person.IPersonService;
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
public class PersonController {


    private final IPersonService personService;

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private ContactMapper contactMapper;

    @GetMapping("/people")
    public ResponseEntity<Object> getPeople(){

        List<PersonWithMembershipViewDTO> people=  personService.getPeople().stream()
                .map(PersonWithMembershipViewDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("People",people));

    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable Long id){
        PersonWithMembershipViewDTO person = personMapper.toPersonWithMembershipViewDTO(personService.getPerson(id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Person",person));
    }

    @PostMapping("/person/create")
    public ResponseEntity<Object> createPerson(@Valid @RequestBody PersonBaseDTO personBaseDTO){

        Person person = personService.createPerson(personMapper.toEntity(personBaseDTO));
        PersonViewDTO newPerson =  personMapper.toPersonWithMembershipViewDTO(person);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Person",newPerson));
    }

    @DeleteMapping("/person/delete/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        if (personService.existsById(id) ){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.failure("Person deletion failed",null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Delete person successfully",null));
    }

    @PutMapping("/person/update/{id}")
    public ResponseEntity<Object> updatedPerson(@Valid @RequestBody PersonBaseDTO personBaseDTO,
                                       @PathVariable Long id){


        Person person = personService.updatePerson(id,personBaseDTO);
        PersonViewDTO updatedPerson = personMapper.toPersonWithMembershipViewDTO(person);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Person updated successfully",updatedPerson));

    }

    @GetMapping("/person/{id}/teams")
    public ResponseEntity<Object> getTeams(@PathVariable("id") Long personId){

        List<MemberTeam> membershipList = personService.getTeams(personId);

        List<MemberTeamViewDTO> membershipsDto = membershipList.stream().map(MemberTeamViewDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Teams the person with ID " + personId + " belongs to",membershipsDto));
    }




}
