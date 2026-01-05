package com.crudconJPAyHibernate.jpaDemo.Dto.MemberTeam;

import com.crudconJPAyHibernate.jpaDemo.Dto.Person.PersonViewDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
public record MembershipViewDTO (
    Long id,
    String rolInTeam,
    LocalDate joinDate,
    boolean active,
    String teamName


){
}
