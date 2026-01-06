package com.tm.teamManagement.Model.Entity;

import com.tm.teamManagement.Exceptions.BadRequestException;
import com.tm.teamManagement.Model.Enum.ContactType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_person", nullable = false, foreignKey = @ForeignKey(name="fk_contact_person"))
    @JsonIgnore
    private Person person;

    //CONTACT TYPE IT COULD BE A SECONDARY EMAIL, A PHONE OR OTHER
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ContactType contactType;

    private String value;
    private boolean major = false;
    private String notes;


    // These annotations allow validation before inserting or updating.
    @PrePersist
    @PreUpdate
    private void valueValidation() {
        if (contactType == ContactType.EMAIL && !value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new BadRequestException("Invalid email format");
        }
        if (contactType == ContactType.PHONE && !value.matches("^\\d{7,15}$")) {
            throw new BadRequestException("Phone must be 7-15 digits");
        }
    }


}
