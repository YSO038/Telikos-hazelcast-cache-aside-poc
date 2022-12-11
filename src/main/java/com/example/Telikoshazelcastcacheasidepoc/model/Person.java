package com.example.Telikoshazelcastcacheasidepoc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ToString
@Table("Person")
@Builder
@AllArgsConstructor
public class Person implements Persistable, Serializable {

    @Id
    @Column("id")
    private Integer id;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("birthdate")
    private LocalDate birthdate;

    @Transient
    private boolean newPerson;

    @Override
    @Transient
    public boolean isNew() {
        return this.newPerson || id == null;
    }

    public Person setAsNew(){
        this.newPerson = true;
        return this;
    }

}