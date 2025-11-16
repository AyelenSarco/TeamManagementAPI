package com.crudconJPAyHibernate.jpaDemo.Model.Entity;

import com.crudconJPAyHibernate.jpaDemo.Model.Enum.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_person",
                    referencedColumnName = "id",
                        nullable = false)
    private Person person;

    private String biography;
    private String avatarUrl;
    private String nationality;
    private Gender gender;
    private String linkedinUrl;

}
