
package com.crudconJPAyHibernate.jpaDemo.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter @Setter
@Entity
@Table(name="person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    private int age;

    @Column(unique = true, nullable = false)
    private String email;
    private LocalDate birthDate;


    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    private LocalDate updatedAt;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<MemberTeam> membership;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void addMemberShip(MemberTeam memberShip) {
        membership.add(memberShip);
    }
            
    }
