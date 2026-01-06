package com.tm.teamManagement.Controller;

import com.tm.teamManagement.Dto.MemberTeam.MemberTeamMapper;
import com.tm.teamManagement.Dto.MemberTeam.MembershipViewDTO;
import com.tm.teamManagement.Dto.Person.PersonBaseDTO;
import com.tm.teamManagement.Dto.Person.PersonMapper;
import com.tm.teamManagement.Dto.Person.PersonViewDTO;
import com.tm.teamManagement.Dto.Response.ApiResponse;
import com.tm.teamManagement.Model.Entity.MemberTeam;
import com.tm.teamManagement.Model.Entity.Person;
import com.tm.teamManagement.Service.Person.IPersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PersonController {


    private final IPersonService personService;

    private final PersonMapper personMapper;
    private final MemberTeamMapper memberTeamMapper;


    @GetMapping("/people")
    public ResponseEntity<Object> getPeople(){

        List<PersonViewDTO> people=  personService.getPeople().stream()
                .map(personMapper::toDto).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("People",people));

    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable Long id){
        PersonViewDTO person = personMapper.toDto(personService.getPerson(id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Person",person));
    }

    @PostMapping("/person/create")
    public ResponseEntity<Object> createPerson(@Valid @RequestBody PersonBaseDTO personBaseDTO){

        Person person = personService.createPerson(personMapper.toEntity(personBaseDTO));
        PersonViewDTO newPerson =  personMapper.toDto(person);

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
        PersonViewDTO updatedPerson = personMapper.toDto(person);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Person updated successfully",updatedPerson));

    }

    @GetMapping("/person/{id}/teams")
    public ResponseEntity<Object> getTeams(@PathVariable("id") Long personId){

        List<MemberTeam> membershipList = personService.getTeams(personId);

        List<MembershipViewDTO> membershipsDto = membershipList.stream()
                .map(memberTeamMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Teams the person with ID " + personId + " belongs to",membershipsDto));
    }




}
