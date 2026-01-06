
package com.tm.teamManagement.Service.Person;


import com.tm.teamManagement.Dto.Person.PersonBaseDTO;
import com.tm.teamManagement.Model.Entity.MemberTeam;
import com.tm.teamManagement.Model.Entity.Person;

import java.util.List;

public interface IPersonService {

    public List<Person> getPeople();
    public Person getPerson(Long id);
    public Person createPerson(Person person);
    public void deletePerson(Long id);
    public Person updatePerson(Long id, PersonBaseDTO personDTO);
    public List<MemberTeam> getTeams(Long personId);
    public boolean existsById(Long id);

}
