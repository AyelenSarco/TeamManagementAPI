
package com.crudconJPAyHibernate.jpaDemo.Service.Person;


import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.MemberTeam;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;

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
